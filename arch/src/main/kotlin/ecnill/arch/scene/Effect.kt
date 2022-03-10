package ecnill.arch.scene

/**
 * Effects are returned by [Reducer]s. Typically, it can be network or database calls that influence UI.
 */
fun interface Effect<out Action> {

    suspend operator fun invoke(): Action
}

/**
 * Wrapper for actions that require launching of some [Effect].
 */
fun <Action> effectOf(effect: Effect<Action>): List<Effect<Action>> = listOf(effect)

/**
 * Wrapper for simple actions with no need of effect.
 */
fun <Type> Mutable<Type>.withNoEffect(mutateFn: Type.() -> Type): List<Effect<Nothing>> {
    mutate(mutateFn)
    return noEffect()
}

/**
 * Can be used as a return type when there is nothing to do anymore (e.g. navigate away from the current screen).
 */
fun noEffect() = emptyList<Effect<Nothing>>()