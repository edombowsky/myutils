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

package com.github.emd.myutils.io

import java.io.File

import scala.io.Codec
import scala.io.Source


object SourceEx {

  def autoCloseFile[T](file: File)(todo: Source => T)(implicit codec: Codec): T = {
    val source = Source.fromFile(file)
    try {
      todo(source)
    }
    finally {
      source.close()
    }
  }

  def autoCloseFile[T](file: File, enc: String)(todo: Source => T): T =
    autoCloseFile(file)(todo)(Codec(enc))
}
