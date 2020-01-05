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

package com.github.emd.myutils.concurrent.duration

import java.util.concurrent.TimeUnit

import scala.concurrent.duration._

import com.github.emd.myutils.misc.Util.parseDuration

/** Duration helpers. */
object Durations {

  /**
   * Gets duration from string.
   *
   * @return parsed duration; None upon format error
   */
  def parse(s: String): Option[Duration] = {
    Option(s).flatMap { v =>
      try {
        Some(parseDuration(v))
      } catch {
        case _: Exception => None
      }
    }
  }

  /**
   * Gets finite duration from string.
   *
   * @return parsed duration; None upon format error or non-finite duration
   */
  def parseFinite(s: String): Option[FiniteDuration] = {
    parse(s).filter(_.isFinite).asInstanceOf[Option[FiniteDuration]]
  }

  /** Gets time unit short representation (ns, us, ms, s, min, h, d). */
  def shortUnit(unit: TimeUnit): String = {
    unit match {
      case TimeUnit.NANOSECONDS  => "ns"
      case TimeUnit.MICROSECONDS => "us"
      case TimeUnit.MILLISECONDS => "ms"
      case TimeUnit.SECONDS      => "s"
      case TimeUnit.MINUTES      => "min"
      case TimeUnit.HOURS        => "h"
      case TimeUnit.DAYS         => "d"
    }
  }
}
