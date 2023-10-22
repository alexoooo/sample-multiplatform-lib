package io.github.alexoooo.sample.lib

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue


class JsPersistentListTest {
    @Test
    fun appendToListPreservesPreviousVersion() {
        val previous = persistentListOf(1, 2, 3)
        val withAppend = previous.add(4)

        assertEquals(previous, listOf(1, 2, 3))
        assertEquals(withAppend, listOf(1, 2, 3, 4))
    }
}