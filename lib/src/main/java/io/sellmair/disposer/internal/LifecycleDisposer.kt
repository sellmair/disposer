package io.sellmair.disposer.internal

import android.arch.lifecycle.GenericLifecycleObserver
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import io.sellmair.disposer.Disposer


/*
################################################################################################
INTERNAL API
################################################################################################
*/

internal class LifecycleDisposer(
    lifecycle: Lifecycle,
    vararg events: Lifecycle.Event) : Disposer by LockedDisposer() {

    private val observer: LifecycleObserver = GenericLifecycleObserver { _, event ->
        if (events.contains(event)) {
            dispose()
        }
    }

    init {
        lifecycle.addObserver(observer)
    }
}