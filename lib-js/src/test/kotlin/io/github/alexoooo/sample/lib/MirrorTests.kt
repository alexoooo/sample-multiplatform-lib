package io.github.alexoooo.sample.lib

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue


class MirrorTests {
    @Test
    fun detectExistenceOfClasses() {
        ModuleRegistry.add(js("require('./lib-lib-js-test')"))
//        println("!!!! " + js("Object.getOwnPropertyNames( window )"))

        val sampleModelClass = ClassName(
            "io.github.alexoooo.sample.lib.SampleModel")

        assertTrue(Mirror.contains(sampleModelClass))

        assertEquals(
            listOf("name", "number"),
            Mirror.constructorArgumentNames(sampleModelClass))

        val sampleModel = Mirror.create(sampleModelClass, listOf("foo", 42.0))
                as SampleModel

        assertEquals("foo", sampleModel.name)
        assertEquals(42.0, sampleModel.number)
    }
}
