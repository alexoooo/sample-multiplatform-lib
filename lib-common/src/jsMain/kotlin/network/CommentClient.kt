package network

import kotlin.coroutines.CoroutineContext
import kotlin.js.json


class CommentClient(coroutineContext: CoroutineContext) {
    private var fallback = false

    private val headers = json(
        "Accept" to "application/json",
        "Content-Type" to "application/json"
    )
}