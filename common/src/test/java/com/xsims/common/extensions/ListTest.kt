package com.xsims.common.extensions

import org.junit.Assert.assertThrows
import org.junit.Test
import org.junit.Test.None

class ListTest {
  @Test
  fun `When not all elements can be cast Then trows ClassCastException`() {
    val listStringAndInteger = listOf("1", 2, 3)
    assertThrows(ClassCastException::class.java) {
      listStringAndInteger.filterIsInstanceOrException<Int>()
    }
  }

  @Test(expected = None::class)
  fun `When all elements can be cast Then does not throw exception WHEN `() {
    val listAny: List<Any> = listOf(1, 2, 3)
    listAny.filterIsInstanceOrException<Int>()
  }
}