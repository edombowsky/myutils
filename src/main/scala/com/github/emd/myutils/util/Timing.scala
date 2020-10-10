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
