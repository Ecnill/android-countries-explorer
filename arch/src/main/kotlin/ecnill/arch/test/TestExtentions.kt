package ecnill.arch.test

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest

@ExperimentalCoroutinesApi
fun <Type> Flow<Type>.lastValue(
    testScope: TestScope = TestScope(UnconfinedTestDispatcher()),
    block: suspend TestScope.() -> Unit = {},
): Type? {
    var value: Type? = null
    testScope.runTest {
        val job = launch { collectLatest { latest -> value = latest } }
        block()
        job.cancel()
    }
    return value
}