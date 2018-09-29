package io.sellmair.rxlifecycle.internal

import android.arch.lifecycle.Lifecycle
import io.sellmair.rxlifecycle.LifecycleDisposers
import java.util.*
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

/*
################################################################################################
INTERNAL IMPLEMENTATION: LifecycleDisposers.Store
################################################################################################
*/

private val disposers = WeakHashMap<Lifecycle, LifecycleDisposers>()
private val disposersLock = ReentrantLock()
internal operator fun LifecycleDisposers.Store.get(lifecycle: Lifecycle):
    LifecycleDisposers = disposersLock.withLock {
    disposers.getOrPut(lifecycle) { LifecycleDisposers.Factory.create(lifecycle) }
}