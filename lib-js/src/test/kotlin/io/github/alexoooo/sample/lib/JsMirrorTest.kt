package io.github.alexoooo.sample.lib

import io.github.alexoooo.sample.lib.model.JsTestModel
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue


class JsMirrorTest {
    @Test
    fun detectExistenceOfJsTestClass() {
//        @Suppress("UnsafeCastFromDynamic")
//        val windowKeys: Array<String> = js("Object.getOwnPropertyNames( window )")
//        println("^^^^ detectExistenceOfJsTestClass - ${windowKeys.toList()}")
//        @Suppress("UnsafeCastFromDynamic")
//        val exportsKeys: Array<String> = js("Object.getOwnPropertyNames( module.exports )")
//        println("^^^^ detectExistenceOfJsTestClass - ${exportsKeys.toList()}")
//        console.log("Module: ", js("module"))

//        ModuleRegistry.add(js("require('lib-lib-js-test')"))
        ModuleRegistry.add(js("module.exports"))

        val sampleModelClass = ClassName(
            "io.github.alexoooo.sample.lib.model.JsTestModel")

        assertTrue(Mirror.contains(sampleModelClass))

        assertEquals(
            listOf("name", "number"),
            Mirror.constructorArgumentNames(sampleModelClass))

        val sampleModel = Mirror.create(sampleModelClass, listOf("foo", 42.0))
                as JsTestModel

        assertEquals("foo", sampleModel.name)
        assertEquals(42.0, sampleModel.number)
    }


    @Test
    fun detectExistenceOfJsMainClass() {
        ModuleRegistry.add(js("require('lib-lib-js')"))
//        ModuleRegistry.add(js("module.exports"))

        val sampleModelClass = ClassName(
            "io.github.alexoooo.sample.lib.model.JsMainModel")

//        val foo = JsMainModel("foo", 42.0)
        assertTrue(Mirror.contains(sampleModelClass))
    }


    @Test
    fun detectExistenceOfCommonClass() {
        ModuleRegistry.add(js("require('lib-lib-common')"))
//        println("!!!! " + js("Object.getOwnPropertyNames( window )"))

        val sampleModelClass = ClassName(
            "io.github.alexoooo.sample.lib.model.CommonModel")

        assertTrue(Mirror.contains(sampleModelClass))
    }
}
