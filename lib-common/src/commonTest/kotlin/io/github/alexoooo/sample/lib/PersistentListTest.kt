package io.github.alexoooo.sample.lib

import kotlin.test.Test
import kotlin.test.assertEquals


class PersistentListTest {
    @Test
    fun initialEmptyShouldHaveSizeZero() {
        assertEquals(0, persistentListOf<String>().size)
    }


    @Test
    fun singleElementShouldHaveSizeOne() {
        val empty = persistentListOf<String>()
        val single = empty.add("foo")
        assertEquals(1, single.size)
        assertEquals("foo", single[0])
    }


    @Test
    fun insertShouldShift() {
        val empty = persistentListOf<String>()
        val single = empty.add("foo")
        val shifted = single.add(0, "bar")
        assertEquals(2, shifted.size)
        assertEquals("bar", shifted[0])
        assertEquals("foo", shifted[1])
    }


    @Test
    fun setShouldReplace() {
        val empty = persistentListOf<String>()
        val single = empty.add("foo")
        val replaced = single.set(0, "bar")
        assertEquals(1, replaced.size)
        assertEquals("bar", replaced[0])
    }


    @Test
    fun removeShouldUnshift() {
        val empty = persistentListOf<String>()
        val twoElement = empty.add("foo").add("bar")
        val removed = twoElement.removeAt(0)
        assertEquals(1, removed.size)
        assertEquals("bar", removed[0])
    }
}