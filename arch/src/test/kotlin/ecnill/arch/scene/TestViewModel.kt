package ecnill.arch.scene

internal class TestViewModel(
    initial: TestState = TestState()
) : ReducibleViewModel.Implementation<TestState, TestAction>(initial, TestReducer())

internal data class TestState(val first: String = "", val second: String = "")

internal sealed interface TestAction {

    data class FirstParameterUpdated(val value: String) : TestAction

    object EffectLaunched : TestAction

    data class SecondParameterUpdated(val value: String) : TestAction
}

internal class TestReducer : Reducer<TestState, TestAction> {

    override fun reduce(state: Mutable<TestState>, action: TestAction): List<Effect<TestAction>> {
        val res = when (action) {
            is TestAction.FirstParameterUpdated -> state.withNoEffect { copy(first = action.value) }
            is TestAction.EffectLaunched -> effectOf(TestEffect())
            is TestAction.SecondParameterUpdated -> state.withNoEffect { copy(second = action.value) }
        }
        return res
    }
}

internal class TestEffect : Effect<TestAction> {

    override suspend fun invoke(): TestAction = TestAction.SecondParameterUpdated(changedByEffect)
}

internal const val changedByEffect = "changed by effect"
internal const val changedWithoutEffect = "changed without effect"