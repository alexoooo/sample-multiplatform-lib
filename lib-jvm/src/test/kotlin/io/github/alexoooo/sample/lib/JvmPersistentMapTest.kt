package io.github.alexoooo.sample.lib

import kotlin.test.Test
import kotlin.test.assertEquals


class JvmPersistentMapTest {
    @Test
    fun appendToListPreservesPreviousVersion() {
        val previous = persistentMapOf("a" to 1)
        val withAppend = previous.put("b", 2)

        assertEquals(previous, mapOf("a" to 1))
        assertEquals(withAppend, mapOf("a" to 1, "b" to 2))
    }
}