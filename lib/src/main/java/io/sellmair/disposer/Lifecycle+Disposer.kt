package io.sellmair.disposer

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleOwner
import io.sellmair.disposer.internal.get

/**
 * Shortcut for ```.disposers.onCreate```
 */
val Lifecycle.onCreate get() = disposers.onCreate

/**
 * Shortcut for ```.disposers.onStart```
 */
val Lifecycle.onStart get() = disposers.onStart

/**
 * Shortcut for ```.disposers.onResume```
 */
val Lifecycle.onResume get() = disposers.onResume

/**
 * Shortcut for ```.disposers.onPause```
 */
val Lifecycle.onPause get() = disposers.onPause

/**
 * Shortcut for ```.disposers.onStop```
 */
val Lifecycle.onStop get() = disposers.onStop

/**
 * Shortcut for ```.disposers.onDestroy```
 */
val Lifecycle.onDestroy get() = disposers.onDestroy

/**
 * Shortcut for ```lifecycle.disposers.onCreate```
 */
val LifecycleOwner.onCreate get() = lifecycle.onCreate

/**
 * Shortcut for ```lifecycle.disposers.onStart```
 */
val LifecycleOwner.onStart get() = lifecycle.onStart

/**
 * Shortcut for ```lifecycle.disposers.onResume```
 */
val LifecycleOwner.onResume get() = lifecycle.onResume

/**
 * Shortcut for ```lifecycle.disposers.onPause```
 */
val LifecycleOwner.onPause get() = lifecycle.onPause

/**
 * Shortcut for ```lifecycle.disposers.onStop```
 */
val LifecycleOwner.onStop get() = lifecycle.onStop

/**
 * Shortcut for ```lifecycle.disposers.onDestroy```
 */
val LifecycleOwner.onDestroy get() = lifecycle.onDestroy


/**
 * The associated disposers for the given lifecycle.
 * There is guaranteed only a single instance for each lifecycle that can be used.
 * All disposers will automatically dispose on the dedicated lifecycle event
 */
val Lifecycle.disposers: LifecycleDisposers get() = LifecycleDisposers.Store[this]

/**
 * Shortcut for ```.lifecycle.disposers```
 * @see Lifecycle.disposers
 */
val LifecycleOwner.disposers: LifecycleDisposers get() = lifecycle.disposers