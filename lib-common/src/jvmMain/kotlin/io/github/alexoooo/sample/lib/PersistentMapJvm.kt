package io.github.alexoooo.sample.lib

import com.github.andrewoma.dexx.collection.HashMap
import com.github.andrewoma.dexx.collection.TreeMap

// https://stackoverflow.com/a/9313962/1941359
// https://youtu.be/lcI-jmh5Cf0
class PersistentMapJvm<K: Any, out V: Any> private constructor(
    private val delegate: HashMap<K, Pair<V, Long>>,
    private val orderDelegate: TreeMap<Long, K>,
    private val insertCount: Long
):
    PersistentMap<K, V>,
    AbstractMap<K, V>()
{
    //-----------------------------------------------------------------------------------------------------------------
    constructor(): this(
        HashMap.empty<K, Pair<V, Long>>(),
        TreeMap<Long, K>(),
        0
    )


    //-----------------------------------------------------------------------------------------------------------------
    override val entries: Set<Map.Entry<K, V>>
        get() {
            return object: AbstractSet<Map.Entry<K, V>>() {
                override val size: Int
                    get() = delegate.size()

                override fun iterator(): Iterator<Map.Entry<K, V>> {
                    return object: Iterator<Map.Entry<K, V>> {
                        private val orderIterator = orderDelegate.values().iterator()

                        override fun hasNext(): Boolean {
                            return orderIterator.hasNext()
                        }

                        override fun next(): Map.Entry<K, V> {
                            val nextKey = orderIterator.next()
                            val nextValue = delegate[nextKey]!!
                            return java.util.AbstractMap.SimpleImmutableEntry(nextKey, nextValue.first)
                        }
                    }
                }
            }
        }


    //-----------------------------------------------------------------------------------------------------------------
    override val size: Int
        get() = delegate.size()


    override fun containsKey(key: K): Boolean {
        return delegate.containsKey(key)
    }


    override operator fun get(key: K): V? {
        return delegate.get(key)?.first
    }


    override val keys: Set<K>
        get() {
            return object: AbstractSet<K>() {
                override val size: Int
                    get() = delegate.size()

                override fun iterator(): Iterator<K> {
                    return orderDelegate.values().iterator()
                }

                override fun contains(element: K): Boolean {
                    return containsKey(element)
                }
            }
        }


    override val values: Collection<V>
        get() {
            return object: AbstractCollection<V>() {
                override val size: Int
                    get() = delegate.size()

                override fun iterator(): Iterator<V> {
                    val delegateIterator = orderDelegate.values().iterator()
                    return object : Iterator<V> {
                        override fun hasNext(): Boolean {
                            return delegateIterator.hasNext()
                        }

                        override fun next(): V {
                            return delegate[delegateIterator.next()]!!.first
                        }
                    }
                }
            }
        }


    //-----------------------------------------------------------------------------------------------------------------
    override fun put(key: K, value: @UnsafeVariance V): PersistentMapJvm<K, V> {
        val existing = delegate[key]

        return if (existing == null) {
            PersistentMapJvm(
                delegate.put(key, value to insertCount),
                orderDelegate.put(insertCount, key),
                insertCount + 1
            )
        }
        else {
            PersistentMapJvm(
                delegate.put(key, value to existing.second),
                orderDelegate,
                insertCount)
        }
    }


    override fun putAll(from: Map<K, @UnsafeVariance V>): PersistentMapJvm<K, V> {
        var buffer = this
        for (e in from) {
            buffer = buffer.put(e.key, e.value)
        }
        return buffer
    }


    override fun remove(key: K): PersistentMapJvm<K, V> {
        val existing = delegate[key]
            ?: return this

        return PersistentMapJvm(
            delegate.remove(key),
            orderDelegate.remove(existing.second),
            insertCount)
    }


    override fun insert(
        key: K,
        value: @UnsafeVariance V,
        position: Int
    ): PersistentMapJvm<K, V> {
        check(key !in this)

        if (position == size) {
            return put(key, value)
        }

        var builder = PersistentMapJvm<K, V>()
        val iterator = orderDelegate.values().iterator()

        var nextIndex = 0
        while (true) {
            if (nextIndex == position) {
                break
            }
            nextIndex++

            val nextKey = iterator.next()
            val nextValue = delegate.get(nextKey)!!
            builder = builder.put(nextKey, nextValue.first)
        }

        builder = builder.put(key, value)

        while (iterator.hasNext()) {
            val nextKey = iterator.next()
            val nextValue = delegate.get(nextKey)!!
            builder = builder.put(nextKey, nextValue.first)
        }

        return builder
    }


    //-----------------------------------------------------------------------------------------------------------------
    override fun equalsInOrder(other: PersistentMap<K, @UnsafeVariance V>): Boolean {
        if (size != other.size) {
            return false
        }

        val orderIterator = orderDelegate.values().iterator()
        val otherOrderIterator = other.keys.iterator()

        while (orderIterator.hasNext()) {
            val nextKey = orderIterator.next()
            val otherNextKey = otherOrderIterator.next()

            if (nextKey != otherNextKey ||
                get(nextKey) != other[nextKey]) {
                return false
            }
        }

        return true
    }
}