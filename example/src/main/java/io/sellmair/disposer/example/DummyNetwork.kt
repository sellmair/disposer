package io.sellmair.disposer.example

import io.reactivex.Observable

object DummyNetwork {
    fun query(): Observable<String> = Observable.just("Hello")
}