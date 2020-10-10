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
