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

package com.github.emd.myutils.util

import scala.concurrent.duration.FiniteDuration
import scala.concurrent.duration.MILLISECONDS

package object Timing {

  /**
    * Run a block and return the block result and the runtime duration
    *
    * @param block
    * @return
    */
  def timeCode[T](block: => T): (T, FiniteDuration) = {
    val start = System.currentTimeMillis
    val result = block
    val end = System.currentTimeMillis
    (result, FiniteDuration(end - start, MILLISECONDS))
  }
}
