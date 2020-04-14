package io.github.alexoooo.sample.lib

import io.github.alexoooo.sample.lib.model.JvmMainModel
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.Test


class JvmMirrorTest {
    @Test
    fun detectExistenceOfJvmMainClass() {
        val sampleModelClass = ClassName(
            "io.github.alexoooo.sample.lib.model.JvmMainModel")

        assertTrue(Mirror.contains(sampleModelClass))

        assertEquals(
            listOf("name", "number"),
            Mirror.constructorArgumentNames(sampleModelClass))

        val sampleModel = Mirror.create(sampleModelClass, listOf("foo", 42.0))
                as JvmMainModel

        assertEquals("foo", sampleModel.name)
        assertEquals(42.0, sampleModel.number)
    }


    @Test
    fun detectExistenceOfCommonClass() {
        val sampleModelClass = ClassName(
            "io.github.alexoooo.sample.lib.model.CommonModel")

        assertTrue(Mirror.contains(sampleModelClass))
    }


    @Test
    fun detectExistenceOfJvmTestClass() {
        val sampleModelClass = ClassName(
            "io.github.alexoooo.sample.lib.model.JvmTestModel")

        assertTrue(Mirror.contains(sampleModelClass))
    }
}