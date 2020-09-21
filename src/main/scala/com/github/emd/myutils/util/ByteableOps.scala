package com.github.emd.myutils.util

import java.nio.ByteBuffer

/** Utilities that help with byte serialization */
object ByteableOps {

  /**
    * Trait that helps classes to be serialised to a byte array.
    */
  trait Byteable extends Any {
    def toByteArray: Array[Byte]
  }

  implicit class XShort(val x: Short) extends AnyVal with Byteable {
    def toByteArray: Array[Byte] = {
      val buf = ByteBuffer.allocate(2)
      buf.putShort(x)
      buf.array
    }
  }

  implicit class XInt(val x: Int) extends AnyVal with Byteable {
    def toByteArray: Array[Byte] = {
      val buf = ByteBuffer.allocate(4)
      buf.putInt(x)
      buf.array
    }
  }

  implicit class XLong(val x: Long) extends AnyVal with Byteable {
    def toByteArray: Array[Byte] = {
      val buf = ByteBuffer.allocate(8)
      buf.putLong(x)
      buf.array
    }
  }

  implicit class XFloat(val x: Float) extends AnyVal with Byteable {
    def toByteArray: Array[Byte] = {
      val buf = ByteBuffer.allocate(4)
      buf.putFloat(x)
      buf.array
    }
  }

  implicit class XDouble(val x: Double) extends AnyVal with Byteable {
    def toByteArray: Array[Byte] = {
      val buf = ByteBuffer.allocate(8)
      buf.putDouble(x)
      buf.array
    }
  }

}
