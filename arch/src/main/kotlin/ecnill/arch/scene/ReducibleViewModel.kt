package ecnill.arch.scene

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * The main contract for [ViewModel]s.
 */
interface ReducibleViewModel<State, Action> {

    val states: StateFlow<State>

    fun dispatch(actions: List<Action>)

    fun dispatch(action: Action) = dispatch(listOf(action))

    /**
     * Android based implementation of [ReducibleViewModel] that uses lifecycle's [viewModelScope].
     */
    abstract class Implementation<State, Action : Any>(
        initialState: State,
        private val reducer: Reducer<State, Action>,
        initialActions: List<Action> = emptyList(),
    ) : ViewModel(), ReducibleViewModel<State, Action> {

        private val mutableStates = MutableStateFlow(initialState)

        private val mutable = Mutable({ mutableStates.value }) { mutableStates.value = it }

        override val states: StateFlow<State> = mutableStates

        init { dispatch(initialActions) }

        final override fun dispatch(actions: List<Action>) {
            if (actions.isEmpty()) return
            viewModelScope.launch {
                val effects = actions.flatMap { action ->
                    reducer.reduce(mutable, action)
                }
                val effectActions = effects.map { effect -> effect() }
                dispatch(effectActions)
            }
        }
    }
}