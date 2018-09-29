package io.sellmair.rxlifecycle

import io.reactivex.*
import io.reactivex.disposables.Disposable

interface Disposer : Disposable {

    /**
     * Disposer is never disposed, because the [dispose] function can be called
     * multiple times, which will just reset the state of the disposer
     */
    override fun isDisposed(): Boolean = false

    /**
     * @param disposable will be disposed once this disposer is disposed.
     */
    fun add(disposable: Disposable)

    /**
     * Syntactic sugar for [add]
     */
    operator fun plusAssign(disposable: Disposable)


    fun <T> bind(observable: Observable<T>): Observable<T>

    fun <T> bind(flowable: Flowable<T>): Flowable<T>

    fun <T> bind(single: Single<T>): Single<T>

    fun <T> bind(maybe: Maybe<T>): Maybe<T>

    fun bind(completable: Completable): Completable
}


fun <T> Observable<T>.disposeBy(disposer: Disposer): Observable<T> = disposer.bind(this)

fun <T> Flowable<T>.disposeBy(disposer: Disposer): Flowable<T> = disposer.bind(this)

fun <T> Single<T>.disposeBy(disposer: Disposer): Single<T> = disposer.bind(this)

fun <T> Maybe<T>.disposeBy(disposer: Disposer): Maybe<T> = disposer.bind(this)

fun Completable.disposeBy(disposer: Disposer): Completable = disposer.bind(this)

fun Disposable.disposeBy(disposer: Disposer) = this.apply { disposer.add(this) }
