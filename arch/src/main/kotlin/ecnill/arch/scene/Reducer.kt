package ecnill.arch.scene

/**
 * Takes the current state, some information about what to do with it, and creates a list of [Effect]s to launch.
 */
fun interface Reducer<State, Action> {

    fun reduce(state: Mutable<State>, action: Action): List<Effect<Action>>
}