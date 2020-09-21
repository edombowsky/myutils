package com.github.emd.myutils.util

import java.util.Properties

import scala.jdk.CollectionConverters.CollectionHasAsScala
import scala.jdk.CollectionConverters.EnumerationHasAsScala

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class CollectionUtilsTest extends AnyFunSuite with Matchers {

  import com.github.emd.myutils.util.CollectionOperations._

  test(
    "map2Properties for an empty map should yield an empty properties instance"
  ) {
    val result = Map[String, String]().asProperties
    result.propertyNames.asScala shouldBe empty
    result.values.asScala shouldBe empty
  }

  test(
    "map2Properties for map of strings should yield the corresponding properties instance"
  ) {
    val result =
      Map[String, String]("key1" -> "val1", "key2" -> "val2").asProperties
    val expected = new Properties()
    expected.setProperty("key1", "val1")
    expected.setProperty("key2", "val2")
    result shouldBe expected
  }

  test(
    "map2Properties for map of ints and booleans should yield the corresponding properties instance"
  ) {
    val result = Map[Int, Boolean](1 -> true, 2 -> false).asProperties
    val expected = new Properties()
    expected.setProperty("1", "true")
    expected.setProperty("2", "false")
    result shouldBe expected
  }

  test("map2HashMap for an empty map should yield an empty HashMap instance") {
    val result = Map[String, String]().asHashMap
    result.keySet.asScala shouldBe empty
    result.values().asScala shouldBe empty
  }

  test(
    "map2HashMap for map of strings should yield the corresponding HashMap instance"
  ) {
    val result =
      Map[String, String]("key1" -> "val1", "key2" -> "val2").asHashMap
    val expected = new java.util.HashMap[String, String]()
    expected.put("key1", "val1")
    expected.put("key2", "val2")
    result shouldBe expected
  }

  test(
    "map2HashMap for map of ints and booleans should yield the corresponding HashMap instance"
  ) {
    val result = Map[Int, Boolean](1 -> true, 2 -> false).asHashMap
    val expected = new java.util.HashMap[Int, Boolean]()
    expected.put(1, true)
    expected.put(2, false)
    result shouldBe expected
  }
}
