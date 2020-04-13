package io.github.alexoooo.sample.lib


object ModuleRegistry {
    private val mutableModules = mutableSetOf<dynamic>()

    fun modules(): List<dynamic> =
        mutableModules.toList()


    init {
        // TODO
//        add(js("require('lib-lib-common.js')"))
//        add(js("require('kzen-lib-common.js')"))
    }


    @Suppress("MemberVisibilityCanBePrivate")
    fun add(module: dynamic) {
        mutableModules.add(module)
    }
}