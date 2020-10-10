package com.github.emd.myutils.akka

import akka.actor.ActorSystem
import com.typesafe.config.Config
import monix.execution.Scheduler

import com.github.emd.myutils.Configuration

object CoreSystem {

  /** Configuration ('suiryc-scala' path). */
  val config: Config = Configuration.loaded.getConfig("misc-scala")

  /** Core akka system. */
  val system = ActorSystem("suiryc-core", config)

  /** Scheduler running with core system execution context. */
  lazy val scheduler: Scheduler = Scheduler(system.dispatcher)

}
