package io.sellmair.rxlifecycle

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LifecycleRegistry


object TestLifecycle {
    fun create(): LifecycleRegistry = TestLifecycleOwner().lifecycle
}

private class TestLifecycleOwner : LifecycleOwner {
    val lifecycle = LifecycleRegistry(this)
    override fun getLifecycle(): Lifecycle {
        return lifecycle
    }
}