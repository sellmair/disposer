package io.sellmair.disposer

import java.util.concurrent.atomic.AtomicInteger
import kotlin.reflect.KProperty

class TestObserver : (Unit) -> Unit {

    private val count = AtomicInteger()

    fun count(): Int = count.get()

    override fun invoke(p1: Unit) {
        count.getAndIncrement()
    }


}

operator fun AtomicInteger.getValue(thisRef: Any?, property: KProperty<*>): Int = this.get()
