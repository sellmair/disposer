package io.sellmair.rxlifecycle.internal

/*
################################################################################################
INTERNAL API
################################################################################################
*/

internal inline fun <reified T> MutableList<T>.poll(): List<T> {
    val list = listOf(*toTypedArray())
    this.clear()
    return list
}