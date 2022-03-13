package io.github.alexoooo.sample.lib


//---------------------------------------------------------------------------------------------------------------------
fun <T> persistentListOf(
        vararg elements: T
): PersistentList<T> {
    var builder = PersistentList<T>()
    for (e in elements) {
        builder = builder.add(e)
    }
    return builder
}


fun <T> List<T>.toPersistentList(): PersistentList<T> {
    if (this is PersistentList) {
        return this
    }

    var builder = PersistentList<T>()
    forEach {
        builder = builder.add(it)
    }
    return builder
}


//---------------------------------------------------------------------------------------------------------------------
fun <K: Any, V: Any> persistentMapOf(vararg pairs: Pair<K, V>): PersistentMap<K, V> {
    var builder = PersistentMap<K, V>()
    for ((k, v) in pairs) {
        builder = builder.put(k, v)
    }
    return builder
}


fun <K: Any, V: Any> Map<K, V>.toPersistentMap(): PersistentMap<K, V> {
    if (this is PersistentMap) {
        return this
    }

    var builder = PersistentMap<K, V>()
    forEach {
        builder = builder.put(it.key, it.value)
    }
    return builder
}


fun <K: Any, V: Any> Iterable<Pair<K, V>>.toPersistentMap(): PersistentMap<K, V> {
    return toMap().toPersistentMap()
}
