package io.sellmair.disposer

import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import java.util.concurrent.atomic.AtomicInteger

class TestObserver : Observer<Unit> {

    private val atomicNextCount = AtomicInteger()

    val nextCount: Int get() = atomicNextCount.get()

    override fun onComplete() {
    }

    override fun onSubscribe(d: Disposable) {
    }

    override fun onNext(t: Unit) {
        atomicNextCount.getAndIncrement()
    }

    override fun onError(e: Throwable) {
    }


}