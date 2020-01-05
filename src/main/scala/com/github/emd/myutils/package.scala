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

package com.github.emd

import com.github.emd.myutils.misc.EnumerationWithAliases

package object myutils {

  // Note: class defined in package object so that we can make it 'implicit'

  /** Enrich Enumeration with case-insensitive name resolver. */
  implicit class RichEnumeration[A <: Enumeration](val enum: A) extends AnyVal {

    private type WithAliases = A with EnumerationWithAliases
    private def withAliases: Option[WithAliases] = enum match {
      case v: EnumerationWithAliases => Some(v.asInstanceOf[WithAliases])
      case _ => None
    }

    def byName(s: String): A#Value = withAliases.map(v => v.byName(s): A#Value).getOrElse {
      enum.values.find(_.toString.toLowerCase == s.toLowerCase).getOrElse {
        throw new NoSuchElementException(s"No value found for '$s'")
      }
    }
  }
}
