package io.github.alexoooo.sample.lib

import io.github.alexoooo.sample.lib.wrap.ImmutableList


class PersistentListJs<out E> private constructor(
    private val delegate: ImmutableList<@UnsafeVariance E>
):
    PersistentList<E>,
    AbstractList<E>()
{
    //-----------------------------------------------------------------------------------------------------------------
    constructor(): this(ImmutableList())


    //-----------------------------------------------------------------------------------------------------------------
    override val size: Int
        get() = delegate.size


    override fun get(index: Int): E {
        return delegate.get(index)
    }


    //-----------------------------------------------------------------------------------------------------------------
    override fun add(element: @UnsafeVariance E): PersistentListJs<E> {
        return PersistentListJs(
            delegate.push(element))
    }


    override fun add(index: Int, element: @UnsafeVariance E): PersistentListJs<E> {
        return PersistentListJs(
            delegate.insert(index, element))
    }


    override fun addAll(elements: List<@UnsafeVariance E>): PersistentListJs<E> {
        var builder = delegate
        for (i in elements) {
            builder = builder.push(i)
        }
        return PersistentListJs(builder)
    }


    override fun set(index: Int, element: @UnsafeVariance E): PersistentListJs<E> {
        return PersistentListJs(
            delegate.set(index, element))
    }


    override fun removeAt(index: Int): PersistentListJs<E> {
        return PersistentListJs(
            delegate.remove(index))
    }


    override fun subList(fromIndex: Int, toIndex: Int): PersistentListJs<E> {
        return PersistentListJs(
            delegate.slice(fromIndex, toIndex))
    }
}