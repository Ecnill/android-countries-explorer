package ecnill.arch.scene

import ecnill.arch.test.TestDispatcherRule
import ecnill.arch.test.lastValue
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class ReducibleViewModelTest {

    @get:Rule
    val coroutinesRule = TestDispatcherRule()

    @Test
    fun `states should emit initial state`() {
        val initial = TestState()
        val viewModel = TestViewModel(initial)

        viewModel
            .states
            .lastValue()
            .let { it shouldBe initial }
    }

    @Test
    fun `should emit state changed without effect`() {
        val viewModel = TestViewModel()
        viewModel.dispatch(TestAction.FirstParameterUpdated(changedWithoutEffect))

        viewModel.states
            .lastValue()
            .let { it?.first shouldBe changedWithoutEffect }
    }

    @Test
    fun `should emit state changed by effect`() = runTest {
        val viewModel = TestViewModel()
        viewModel.dispatch(TestAction.EffectLaunched)

        viewModel.states
            .lastValue()
            .let { it?.second shouldBe changedByEffect }
    }
}
