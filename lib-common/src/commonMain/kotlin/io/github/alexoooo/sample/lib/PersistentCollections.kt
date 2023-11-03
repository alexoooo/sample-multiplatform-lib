package io.github.alexoooo.sample.lib


//---------------------------------------------------------------------------------------------------------------------
expect fun <T> persistentListOf(): PersistentList<T>
expect fun <K: Any, V: Any>  persistentMapOf(): PersistentMap<K, V>


//---------------------------------------------------------------------------------------------------------------------
fun <T> persistentListOf(
    vararg elements: T
): PersistentList<T> {
    var builder = persistentListOf<T>()
    for (e in elements) {
        builder = builder.add(e)
    }
    return builder
}


fun <T> List<T>.toPersistentList(): PersistentList<T> {
    if (this is PersistentList) {
        return this
    }

    var builder = persistentListOf<T>()
    forEach {
        builder = builder.add(it)
    }
    return builder
}


//---------------------------------------------------------------------------------------------------------------------
fun <K: Any, V: Any> persistentMapOf(vararg pairs: Pair<K, V>): PersistentMap<K, V> {
    var builder = persistentMapOf<K, V>()
    for ((k, v) in pairs) {
        builder = builder.put(k, v)
    }
    return builder
}


fun <K: Any, V: Any> Map<K, V>.toPersistentMap(): PersistentMap<K, V> {
    if (this is PersistentMap) {
        return this
    }

    var builder = persistentMapOf<K, V>()
    forEach {
        builder = builder.put(it.key, it.value)
    }
    return builder
}


fun <K: Any, V: Any> Iterable<Pair<K, V>>.toPersistentMap(): PersistentMap<K, V> {
    return toMap().toPersistentMap()
}