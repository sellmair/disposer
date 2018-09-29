@file:Suppress("unused")

package io.sellmair.rxlifecycle.internal

import android.arch.lifecycle.Lifecycle
import io.sellmair.rxlifecycle.LifecycleDisposers


/*
################################################################################################
INTERNAL IMPLEMENTATION: LifecycleDisposers.Factory
################################################################################################
*/

internal fun LifecycleDisposers.Factory.create(lifecycle: Lifecycle): LifecycleDisposers {
    return LifecycleDisposers(
        onCreate = LifecycleDisposer(lifecycle, Lifecycle.Event.ON_CREATE),
        onStart = LifecycleDisposer(lifecycle, Lifecycle.Event.ON_START),
        onResume = LifecycleDisposer(lifecycle, Lifecycle.Event.ON_RESUME),
        onPause = LifecycleDisposer(lifecycle, Lifecycle.Event.ON_PAUSE),
        onStop = LifecycleDisposer(lifecycle, Lifecycle.Event.ON_STOP),
        onDestroy = LifecycleDisposer(lifecycle, Lifecycle.Event.ON_DESTROY))
}