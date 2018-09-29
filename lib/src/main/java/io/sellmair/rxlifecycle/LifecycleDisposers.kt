@file:Suppress("unused")

package io.sellmair.rxlifecycle

import android.arch.lifecycle.Lifecycle
import io.reactivex.disposables.Disposable

/*
################################################################################################
PUBLIC API
################################################################################################
*/

/**
 * Object holding all possible disposers for a given [Lifecycle] object.
 * Each [Lifecycle] owns one single instance of this disposers
 *
 * @see Lifecycle.disposers
 * @see Lifecycle.onCreate
 * @see Lifecycle.onStart
 * @see Lifecycle.onResume
 * @see Lifecycle.onPause
 * @see Lifecycle.onPause
 * @see Lifecycle.onStop
 * @see Lifecycle.onDestroy
 */
class LifecycleDisposers(
    /**
     * Disposer that will invoke [Disposable.dispose] on all registered disposables
     * anytime the associated lifecycle emits the [Lifecycle.Event.ON_CREATE] event.
     */
    val onCreate: Disposer,

    /**
     * Disposer that will invoke [Disposable.dispose] on all registered disposables
     * anytime the associated lifecycle emits the [Lifecycle.Event.ON_START] event.
     */
    val onStart: Disposer,

    /**
     * Disposer that will invoke [Disposable.dispose] on all registered disposables
     * anytime the associated lifecycle emits the [Lifecycle.Event.ON_RESUME] event.
     */
    val onResume: Disposer,

    /**
     * Disposer that will invoke [Disposable.dispose] on all registered disposables
     * anytime the associated lifecycle emits the [Lifecycle.Event.ON_PAUSE] event.
     */
    val onPause: Disposer,

    /**
     * Disposer that will invoke [Disposable.dispose] on all registered disposables
     * anytime the associated lifecycle emits the [Lifecycle.Event.ON_STOP] event.
     */
    val onStop: Disposer,

    /**
     * Disposer that will invoke [Disposable.dispose] on all registered disposables
     * anytime the associated lifecycle emits the [Lifecycle.Event.ON_DESTROY] event.
     */
    val onDestroy: Disposer) {

    /*
    ################################################################################################
    INTERNAL API
    ################################################################################################
    */

    /**
     * Factory for [LifecycleDisposers]
     * @see Factory.create
     */
    internal object Factory

    /**
     * Each [Lifecycle] will have it's own dedicated [LifecycleDisposers] object which
     * can be retrieved from this store.
     *
     * @see Store.get
     */
    internal object Store

}




