package io.github.alexoooo.sample.lib


expect object Mirror {
    fun contains(className: ClassName): Boolean

    fun constructorArgumentNames(className: ClassName): List<String>

    fun create(className: ClassName, constructorArguments: List<Any?>): Any
}