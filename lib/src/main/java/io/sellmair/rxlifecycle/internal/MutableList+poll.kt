package io.sellmair.rxlifecycle.internal

/*
################################################################################################
INTERNAL API
################################################################################################
*/

/**
 * @receiver will be cleared
 * @return Copy of the current list before cleared
 */
internal inline fun <reified T> MutableList<T>.poll(): List<T> {
    val list = listOf(*toTypedArray())
    this.clear()
    return list
}