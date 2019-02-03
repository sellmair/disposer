@file:Suppress("unused")

package io.sellmair.disposer

/**
 * @return A new [Disposer] object that can be independently disposed, but will also receive
 * all [Disposer.dispose] calls from the master disposer.
 *
 * @see Disposer.create
 */
fun Disposer.createSlave(): Disposer {
    return Disposer(master = this)
}