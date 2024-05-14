package com.example.marsphotos.fake

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

//The primary goal of this test rule is to replace the Main dispatcher with a test dispatcher before a test begins to execute.

// The TestWatcher class enables you to take actions on different execution phases of a test.
class TestDispatcherRule @OptIn(ExperimentalCoroutinesApi::class) constructor(
// This parameter enables the use of different dispatchers, such as StandardTestDispatcher.
// This constructor parameter needs to have a default value set to an instance of the UnconfinedTestDispatcher object.
// The UnconfinedTestDispatcher class inherits from the TestDispatcher class and it specifies that tasks must not be executed in any particular order.
    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher(),
) : TestWatcher() {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun starting(description: Description?) {
        Dispatchers.setMain(testDispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun finished(description: Description?) {
        Dispatchers.resetMain()
    }

}