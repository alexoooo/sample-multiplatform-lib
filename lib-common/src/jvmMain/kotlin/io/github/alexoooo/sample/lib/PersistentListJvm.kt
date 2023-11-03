package io.github.alexoooo.sample.lib

import com.github.andrewoma.dexx.collection.Vector


class PersistentListJvm<out E> private constructor(
    private val delegate: Vector<@UnsafeVariance E>
):
    PersistentList<E>,
    AbstractList<E>()
{
    //-----------------------------------------------------------------------------------------------------------------
    constructor() : this(Vector.empty<E>())


    //-----------------------------------------------------------------------------------------------------------------
    override val size: Int
        get() = delegate.size()


    override fun get(index: Int): E {
        return delegate.get(index)
    }


    //-----------------------------------------------------------------------------------------------------------------
    override fun add(element: @UnsafeVariance E): PersistentListJvm<E> {
        return PersistentListJvm(delegate.append(element))
    }


    override fun add(index: Int, element: @UnsafeVariance E): PersistentListJvm<E> {
        var builder: Vector<E> = delegate.take(index)

        builder = builder.append(element)

        for (i in index until size) {
            builder = builder.append(get(i))
        }

        return PersistentListJvm(builder)
    }


    override fun addAll(elements: List<@UnsafeVariance E>): PersistentListJvm<E> {
        var builder = delegate
        for (i in elements) {
            builder = builder.append(i)
        }
        return PersistentListJvm(builder)
    }


    override fun set(index: Int, element: @UnsafeVariance E): PersistentListJvm<E> {
        return PersistentListJvm(
            delegate.set(index, element))
    }


    override fun removeAt(index: Int): PersistentListJvm<E> {
        if (index == 0) {
            return PersistentListJvm(delegate.drop(1))
        }

        // https://groups.google.com/forum/#!topic/scala-user/fZ1TTNgneW4
        // https://lacuna.io/docs/bifurcan/io/lacuna/bifurcan/List.html#concat-io.lacuna.bifurcan.IList-

        var builder: Vector<E> = delegate.take(index)
        for (i in index + 1 until size) {
            if (i == index) {
                continue
            }
            builder = builder.append(get(i))
        }
        return PersistentListJvm(builder)
    }


    override fun subList(fromIndex: Int, toIndex: Int): PersistentListJvm<E> {
        return PersistentListJvm(
            delegate.range(fromIndex, true, toIndex, false))
    }
}