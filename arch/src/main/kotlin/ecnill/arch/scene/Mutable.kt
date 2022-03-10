package ecnill.arch.scene

/**
 * A utility class to easily mutate state represented by simple data class with immutable values.
 */
class Mutable<Type>(private val getValue: () -> Type, private val setValue: (Type) -> Unit) {

    fun get() = getValue()

    fun mutate(apply: Type.() -> (Type)) {
        setValue(apply(getValue()))
    }
}