package com.xsims.test.shared

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class TestCoroutineRule : TestRule {

  val testCoroutineDispatcher = StandardTestDispatcher()
  private val testCoroutineScope = TestScope(testCoroutineDispatcher)

  override fun apply(base: Statement, description: Description): Statement = object : Statement() {
    @Throws(Throwable::class)
    override fun evaluate() {
      Dispatchers.setMain(testCoroutineDispatcher)
      base.evaluate()
      Dispatchers.resetMain()
    }
  }

  fun runTest(block: suspend TestScope.() -> Unit) = testCoroutineScope.runTest {
    block()
  }
}