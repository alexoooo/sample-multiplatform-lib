package io.github.alexoooo.sample.lib


actual data class ClassName actual constructor(
    private val jvmClassName: String
) {
    actual fun get(): String {
        return jvmClassName.replace('$', '.')
    }


    override fun toString(): String {
        return jvmClassName
    }
}