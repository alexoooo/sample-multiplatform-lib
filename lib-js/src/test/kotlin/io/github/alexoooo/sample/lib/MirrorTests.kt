package io.github.alexoooo.sample.lib

import kotlin.test.Test
import kotlin.test.assertTrue


class MirrorTests {
    @Test
    fun detectExistenceOfClasses() {
//        ModuleRegistry.add(js("require('proj-js.js')"))

        val sampleModelClass = ClassName(
            "io.github.alexoooo.sample.lib.SampleModel")

        assertTrue(Mirror.contains(sampleModelClass))
    }
}
