@file:Suppress("unused")

package io.github.alexoooo.sample.lib


actual fun <T> persistentListOf(): PersistentList<T> {
    return PersistentListJs()
}


actual fun <K: Any, V: Any> persistentMapOf(): PersistentMap<K, V> {
    return PersistentMapJs()
}
