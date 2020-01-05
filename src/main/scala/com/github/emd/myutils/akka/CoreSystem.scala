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
