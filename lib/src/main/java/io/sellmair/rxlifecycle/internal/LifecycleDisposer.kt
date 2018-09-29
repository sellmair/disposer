package io.sellmair.rxlifecycle.internal

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.OnLifecycleEvent
import io.reactivex.disposables.Disposables
import io.sellmair.rxlifecycle.Disposer


class LifecycleDisposer(
    private val lifecycle: Lifecycle,
    vararg events: Lifecycle.Event) : Disposer by LockedDisposer() {

    private val observer: LifecycleObserver = object : LifecycleObserver {
        @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
        fun onAny(source: LifecycleOwner, event: Lifecycle.Event) {
            if (events.contains(event)) {
                dispose()
            }
        }
    }

    init {
        lifecycle.addObserver(observer)
        this += Disposables.fromRunnable {
            lifecycle.removeObserver(observer)
        }
    }
}