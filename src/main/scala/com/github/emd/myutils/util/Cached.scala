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

/** Computed value caching. */
class Cached[A](compute: () => A) extends (() => A) {

  // Cached value (if already computed)
  private var cached = Option.empty[A]

  /** Gets the value (and caches it). */
  def value: A = this.synchronized {
    cached.getOrElse {
      val v = compute()
      cached = Some(v)
      v
    }
  }

  override def apply(): A = value

  /** Invalidates the cached value. */
  def invalidate(): Unit = this.synchronized {
    cached = None
  }

  /** Re-computes value: invalidates and gets value. */
  def recompute(): A = {
    invalidate()
    value
  }
}

object Cached {

  def apply[A](compute: () => A): Cached[A] = new Cached(compute)
}
