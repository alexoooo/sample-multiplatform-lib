package io.github.alexoooo.sample.lib

import kotlinx.browser.window


data class CommonJsModel(
    val foo: String
) {
    fun bar() {
        window.closed
    }
}