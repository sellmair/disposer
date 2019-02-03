package io.sellmair.disposer

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry


object TestLifecycle {
    fun create(): LifecycleRegistry = TestLifecycleOwner().lifecycle
}

private class TestLifecycleOwner : LifecycleOwner {
    val lifecycle = LifecycleRegistry(this)
    override fun getLifecycle(): Lifecycle {
        return lifecycle
    }
}