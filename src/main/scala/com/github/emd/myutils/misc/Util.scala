package com.github.emd.myutils.misc

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

import scala.concurrent.duration.Duration
import scala.reflect.ClassTag

object Util {

  /**
    * Wraps null array.
    * Replaces null by empty array if needed.
    *
    * @param a array to wrap
    * @return non-null array
    */
  def wrapNull[T: ClassTag](a: Array[T]): Array[T] =
    Option(a).getOrElse(new Array[T](0))

  /**
    * Finds array element index.
    *
    * @param arr array to search in
    * @param a element to find
    * @param offset offset to start search
    * @param length length to search for
    * @return index of first found element, or -1
    */
  def indexOf[A](arr: Array[A], a: A, offset: Int, length: Int): Int = {
    val idxStart = math.max(0, offset)
    val idxLimit = math.min(arr.length, offset + length)
    @scala.annotation.tailrec
    def loop(idx: Int): Int = {
      if (idx >= idxLimit) -1
      else if (arr(idx) == a) idx
      else loop(idx + 1)
    }
    loop(idxStart)
  }

  /**
    * Gets a class location.
    * Gets path for given class: parent folder of its jar, or the running folder.
    */
  def classLocation[T: ClassTag]: Path = {
    val ct = implicitly[ClassTag[T]]
    // See: http://stackoverflow.com/a/12733172
    val appPath = Paths.get(
      ct.runtimeClass.getProtectionDomain.getCodeSource.getLocation.toURI
    )
    // We either got the class jar (file), or the running folder
    if (Files.isDirectory(appPath)) appPath
    else appPath.getParent
  }

  private val FINITE_DURATION_REGEXP = """^([+-]?[0-9]+)\s*([a-zA-Z]+)$""".r

  /**
    * Parses Duration as a String.
    *
    * Unlike original Duration parsing, tries to keep the original unit when
    * possible.
    *
    * @param s string to parse
    * @return parsed Duration
    */
  def parseDuration(s: String): Duration = {
    // Duration(s) does parse value as Double and fallbacks to Long if
    // value is beyond Double precision. However when using a Double value
    // it converts the duration to nanoseconds and gets the most sensible
    // value/unit from it.
    // This have the potentially undesirable effect of having "60s" give
    // "1 minute" (change of unit).
    // As a side note, there is no similar 'issue' with Config.getDuration
    // as it does not retain the original unit but only the value separated in
    // seconds and nanoseconds.
    s.trim match {
      case FINITE_DURATION_REGEXP(length, unit) => Duration(length.toLong, unit)
      case _                                    => Duration(s)
    }
  }
}
