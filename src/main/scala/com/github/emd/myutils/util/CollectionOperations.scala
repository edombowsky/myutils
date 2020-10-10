package com.github.emd.myutils.util

object CollectionOperations {

  /** Decorate Scala Maps with various utility functions */
  implicit class MapOps[K, V](val map: Map[K, V]) {
    def asProperties: java.util.Properties = CollectionUtils.map2Properties(map)
    def asHashMap: java.util.HashMap[K, V] = CollectionUtils.map2HashMap(map)
  }
}
