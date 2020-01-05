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

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.attribute.BasicFileAttributes
import java.nio.file.attribute.FileTime
import java.util.zip.ZipEntry


class FileTimes(
  val creation: FileTime,
  val lastModified: FileTime,
  val lastAccess: FileTime
)

object FileTimes {

  def apply(creation: FileTime, lastModified: FileTime, lastAccess: FileTime, time: Long): FileTimes = {
    def orNull(time: FileTime): FileTime =
      Option(time).find(_.toMillis > 0).orNull

    def orTime(time1: FileTime, time2: FileTime): FileTime =
      Option(orNull(time1)).getOrElse(orNull(time2))

    new FileTimes(
      orNull(creation),
      orTime(lastModified, FileTime.fromMillis(time)),
      orNull(lastAccess)
    )
  }

  def apply(entry: ZipEntry): FileTimes =
    FileTimes(
      entry.getCreationTime,
      entry.getLastModifiedTime,
      entry.getLastAccessTime,
      entry.getTime
    )

  def apply(path: Path): FileTimes = {
    val attr = Files.readAttributes(path, classOf[BasicFileAttributes])
    FileTimes(
      attr.creationTime(),
      attr.lastModifiedTime(),
      attr.lastAccessTime(),
      path.toFile.lastModified()
    )
  }
}
