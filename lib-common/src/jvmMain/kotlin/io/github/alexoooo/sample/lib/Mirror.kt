package io.github.alexoooo.sample.lib

import kotlin.reflect.full.primaryConstructor


actual object Mirror {
    actual fun contains(className: ClassName): Boolean {
        return try {
            Class.forName(className.get())
            true
        }
        catch (e: ClassNotFoundException) {
            false
        }
    }


    actual fun constructorArgumentNames(className: ClassName): List<String> {
        val type = Class.forName(className.get()).kotlin
        return type.primaryConstructor!!.parameters.map { it.name!! }
    }


    actual fun create(className: ClassName, constructorArguments: List<Any?>): Any {
        try {
            val type = Class.forName(className.get()).kotlin
            return type.primaryConstructor!!.call(*constructorArguments.toTypedArray())
        }
        catch (e: Throwable) {
            throw IllegalArgumentException("Unable to create $className - $constructorArguments", e)
        }
    }
}