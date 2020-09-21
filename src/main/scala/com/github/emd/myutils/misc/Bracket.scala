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

package com.github.emd.myutils.misc

import scala.util.Try

object Bracket {

  /**
    * The `Bracket` tries running a `code` block that uses a `resource` which is silently closed on return using the
    * provided `close` function.
    *
    * @param resource resource creation block
    * @param close resource closing block
    * @param code code that uses the resource
    * @tparam R resource type which must be closed by `close`
    * @tparam T the code block return type
    * @return Success if the resource was successfully initialised and the code was successfully ran,
    *         even if the resource was not successfully closed.
    */
  def apply[R, T](resource: => R)(close: R => Unit)(code: R => T): Try[T] = {
    val res = Try(resource)
    val result = res.map(code)
    res.map(r => Try(close(r)))
    result
  }

  /**
    * The `Bracket.auto` tries running a `code` block that uses a `resource` which is silently closed on return.
    * @param resource resource creation block
    * @param code code that uses the resource
    * @tparam R resource type which must be [[AutoCloseable]]
    * @tparam T the code block return type
    * @return Success if the resource was successfully initialised and the code was successfully ran,
    *         even if the resource was not successfully closed.
    */
  def auto[R <: AutoCloseable, T](resource: => R)(code: R => T): Try[T] =
    Bracket(resource)(_.close())(code)
}
