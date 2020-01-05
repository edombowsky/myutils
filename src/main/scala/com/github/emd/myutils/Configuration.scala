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

package com.github.emd.myutils

import com.typesafe.config.Config
import com.typesafe.config.ConfigFactory
import com.typesafe.config.ConfigParseOptions

object Configuration {

  /** Application configuration, with our reference.conf overriding. */
  val loaded: Config = {
    // Load configuration and have our own reference.conf overriding inserted at
    // the right place. See https://github.com/lightbend/config/issues/342
    // See how it is handled in Play:
    // https://github.com/playframework/playframework/blob/master/framework/src/play/src/main/scala/play/api/Configuration.scala
    // Note: parseResources do not resolve values, which is what we want here -
    // at least in our reference.conf we point to a value that is present in
    // our reference-overrides.conf.
    List(
      ConfigFactory.defaultOverrides(),
      ConfigFactory.defaultApplication(ConfigParseOptions.defaults.setAllowMissing(true)),
      ConfigFactory.parseResources("misc-scala/application-overrides.conf"),
      ConfigFactory.parseResources("application.conf"),
      ConfigFactory.defaultReference()
    ).reduceLeft(_.withFallback(_)).resolve()
  }
}
