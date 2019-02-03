@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package io.sellmair.disposer

import io.reactivex.*
import io.reactivex.disposables.Disposable
import io.sellmair.disposer.internal.LockedDisposer


/**
 * A Disposer is parent of multiple [Disposable] objects that will be disposed
 * once the disposer receives a [Disposer.dispose] event.
 *
 * Disposers instances can be reused and allow for multiple [Disposer.dispose] calls.
 * Be aware, that this fact means, that a [Disposer] will therefore always
 * return true in [isDisposed]
 */
interface Disposer : Disposable {

    /**
     * Disposer is never disposed, because the [Disposer.dispose] function can be called
     * multiple times, which will just reset the state of the disposer
     */
    override fun isDisposed(): Boolean = false

    /**
     * @param disposable will be disposed once this disposer receives a [Disposer.dispose] signal
     */
    fun add(disposable: Disposable)

    /**
     * Syntactic sugar for [add]
     */
    operator fun plusAssign(disposable: Disposable)

    /**
     * Will dispose the upstream once this disposer receives a [Disposer.dispose] signal
     */
    fun <T> bind(observable: Observable<T>): Observable<T>

    /**
     * Will dispose the upstream once this disposer receives a [Disposer.dispose] signal
     */
    fun <T> bind(flowable: Flowable<T>): Flowable<T>

    /**
     * Will dispose the upstream once this disposer receives a [Disposer.dispose] signal
     */
    fun <T> bind(single: Single<T>): Single<T>

    /**
     * Will dispose the upstream once this disposer receives a [Disposer.dispose] signal
     */
    fun <T> bind(maybe: Maybe<T>): Maybe<T>

    /**
     * Will dispose the upstream once this disposer receives a [Disposer.dispose] signal
     */
    fun bind(completable: Completable): Completable


    companion object {

        /**
         *  Syntactically sweeter version of [create]
         *  
         *  @see create
         */
        operator fun invoke(master: Disposer? = null) = this.create(master)

        /**
         * Creates a new thread safe [Disposer] instance.
         * @param master: The newly created disposer instance will receive all all
         * [Disposer.dispose] events from this master disposer and will also dispose on
         * each of those events.
         */
        fun create(master: Disposer? = null): Disposer {
            return LockedDisposer().apply { master?.add(this) }
        }
    }
}


/**
 * Will dispose the upstream once this disposer receives a [Disposer.dispose] signal
 * @see Disposer.bind
 */
fun <T> Observable<T>.disposeBy(disposer: Disposer): Observable<T> = disposer.bind(this)

/**
 * Will dispose the upstream once this disposer receives a [Disposer.dispose] signal
 * @see Disposer.bind
 */
fun <T> Flowable<T>.disposeBy(disposer: Disposer): Flowable<T> = disposer.bind(this)

/**
 * Will dispose the upstream once this disposer receives a [Disposer.dispose] signal
 * @see Disposer.bind
 */
fun <T> Single<T>.disposeBy(disposer: Disposer): Single<T> = disposer.bind(this)

/**
 * Will dispose the upstream once this disposer receives a [Disposer.dispose] signal
 * @see Disposer.bind
 */
fun <T> Maybe<T>.disposeBy(disposer: Disposer): Maybe<T> = disposer.bind(this)

/**
 * Will dispose the upstream once this disposer receives a [Disposer.dispose] signal
 * @see Disposer.bind
 */
fun Completable.disposeBy(disposer: Disposer): Completable = disposer.bind(this)

/**
 * Will Disposer.dispose once this disposer receives a [Disposer.dispose] signal
 * @see Disposer.bind
 */
fun Disposable.disposeBy(disposer: Disposer) = this.apply { disposer.add(this) }
