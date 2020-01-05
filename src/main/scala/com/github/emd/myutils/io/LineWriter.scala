/*
 * Copyright 2020 Earl Dombowsky
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.emd.myutils.io

import akka.actor.Actor
import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor.Props

import com.github.emd.myutils.akka.CoreSystem

/** Writer expecting full lines. */
trait LineWriter {

  /**
   * Write one line.
   *
   * Note: line must not contain line ending (CR and/or LF).
   */
  def write(line: String): Unit
}


class ProxyLineWriter(_writers: Seq[LineWriter] = Seq.empty, async: Boolean = false)
  extends LineWriter {

  protected var writers: Set[LineWriter] = _writers.toSet

  protected val system: ActorSystem = CoreSystem.system
  protected val actor: Option[ActorRef] =
    if (!async) None
    else Some(system.actorOf(Props(new ProxyActor).withDispatcher("dispatcher")))

  override def write(line: String): Unit = {
    actor.fold {
      write(writers, line)
    } {
      _ ! line
    }
  }

  def addWriter(writer: LineWriter): Unit = {
    actor.fold {
      writers += writer
    } {
      _ ! AddWriter(writer)
    }
  }

  def removeWriter(writer: LineWriter): Unit = {
    actor.fold {
      writers -= writer
    } {
      _ ! RemoveWriter(writer)
    }
  }

  @inline private def write(writers: Set[LineWriter], line: String): Unit = {
    writers.foreach(_.write(line))
  }

  private case class AddWriter(writer: LineWriter)
  private case class RemoveWriter(writer: LineWriter)

  private class ProxyActor extends Actor {

    override def receive: Receive = proxy(writers)

    def proxy(writers: Set[LineWriter]): Receive = {
      case line: String =>
        write(writers, line)

      case AddWriter(writer) =>
        context.become(proxy(writers + writer))

      case RemoveWriter(writer) =>
        context.become(proxy(writers - writer))
    }
  }
}
