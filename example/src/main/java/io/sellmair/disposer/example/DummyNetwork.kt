package io.sellmair.disposer.example

import io.reactivex.rxjava3.core.Observable

object DummyNetwork {
    fun query(): Observable<String> = Observable.just("Hello")
}
