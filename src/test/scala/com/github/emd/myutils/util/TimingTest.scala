package com.github.emd.myutils.util

import org.scalatest.matchers.should.Matchers
import org.scalatest.funsuite.AnyFunSuite

import com.github.emd.myutils.util.Timing.timeCode

class TimingTest extends AnyFunSuite with Matchers {

  test(
    "timeCode should return the approximate length of the computation in millis"
  ) {

    val expectedResultMillis = 10
    val expectedDurationMillis = 200
    val (result, duration) = timeCode {
      Thread.sleep(expectedDurationMillis);
      10
    }
    result shouldBe expectedResultMillis
    Math.abs(
      duration.toMillis - expectedDurationMillis
    ) / expectedDurationMillis < 0.1 shouldBe true
  }
}
