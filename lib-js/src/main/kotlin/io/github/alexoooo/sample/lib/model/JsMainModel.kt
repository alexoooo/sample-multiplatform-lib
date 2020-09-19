package io.github.alexoooo.sample.lib.model

import kotlinx.browser.window


data class JsMainModel(
    val name: String,
    val number: Double
) {
    fun foo() {
        window.closed
    }
}