package io.sellmair.rxlifecycle

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleOwner
import io.sellmair.rxlifecycle.internal.get


val Lifecycle.onCreate get() = LifecycleDisposers.Store[this].onCreate
val Lifecycle.onStart get() = LifecycleDisposers.Store[this].onStart
val Lifecycle.onResume get() = LifecycleDisposers.Store[this].onResume
val Lifecycle.onPause get() = LifecycleDisposers.Store[this].onPause
val Lifecycle.onStop get() = LifecycleDisposers.Store[this].onStop
val Lifecycle.onDestroy get() = LifecycleDisposers.Store[this].onDestroy

val LifecycleOwner.onCreate get() = lifecycle.onCreate
val LifecycleOwner.onStart get() = lifecycle.onStart
val LifecycleOwner.onResume get() = lifecycle.onResume
val LifecycleOwner.onPause get() = lifecycle.onPause
val LifecycleOwner.onStop get() = lifecycle.onStop
val LifecycleOwner.onDestroy get() = lifecycle.onDestroy


val Lifecycle.disposers: LifecycleDisposers get() = LifecycleDisposers.Store[this]
val LifecycleOwner.disposers: LifecycleDisposers get() = lifecycle.disposers