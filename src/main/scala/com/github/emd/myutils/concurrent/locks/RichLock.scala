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

package com.github.emd.myutils.concurrent.locks

import java.util.concurrent.locks.Lock

/**
 * Rich lock.
 *
 * Adds helper to automatically lock/unlock around some code.
 */
class RichLock(val underlying: Lock) extends AnyVal {

  def withLock[A](f: => A): A = {
    underlying.lock()
    try {
      f
    } finally {
      underlying.unlock()
    }
  }
}

object RichLock {

  import scala.language.implicitConversions

  implicit def toRichLock(lock: Lock): RichLock =
    new RichLock(lock)
}
