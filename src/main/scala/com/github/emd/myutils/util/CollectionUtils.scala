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

/** Explicit utility functions for working with various collections */
object CollectionUtils {

  /** Convert a Scala Map into a Java Properties instance */
  def map2Properties[K, V](map: Map[K, V]): java.util.Properties = {
    val properties = new java.util.Properties()
    map.foreach { case (k, v) =>
      properties.setProperty(k.toString, v.toString)
    }
    properties
  }

  /** Convert a Scala Map into a Java HashMap instance */
  def map2HashMap[K, V](map: Map[K, V]): java.util.HashMap[K, V] = {
    val hashmap = new java.util.HashMap[K, V]()
    map.foreach { case (k, v) => hashmap.put(k, v) }
    hashmap
  }
}
