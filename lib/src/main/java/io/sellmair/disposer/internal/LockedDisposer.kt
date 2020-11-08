package io.sellmair.disposer.internal

import io.reactivex.rxjava3.core.*
import io.reactivex.rxjava3.disposables.Disposable
import io.sellmair.disposer.Disposer
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

/*
################################################################################################
INTERNAL API
################################################################################################
*/

internal class LockedDisposer : Disposer {

    /**
     * Lock used to synchronize the access of [disposables]
     */
    private val disposablesLock = ReentrantLock()

    /**
     * All currently held disposables that are waiting for the next [dispose] signal.
     * Synchronized via [disposablesLock]
     */
    private val disposables = mutableListOf<Disposable>()

    /**
     * Lock used to synchronize the [dispose] function
     */
    private val disposeLock = ReentrantLock()

    override fun add(disposable: Disposable): Unit = disposablesLock.withLock {
        disposables.add(disposable)
    }

    override fun plusAssign(disposable: Disposable) = add(disposable)

    override fun <T> bind(observable: Observable<T>): Observable<T> {
        return observable.doOnSubscribe { disposable ->
            add(disposable)
        }
    }

    override fun <T> bind(flowable: Flowable<T>): Flowable<T> {
        return flowable.doOnSubscribe { subscription ->
            this += Disposable.fromSubscription(subscription)
        }
    }

    override fun <T> bind(single: Single<T>): Single<T> {
        return single.doOnSubscribe(this::add)
    }

    override fun <T> bind(maybe: Maybe<T>): Maybe<T> {
        return maybe.doOnSubscribe(this::add)
    }

    override fun bind(completable: Completable): Completable {
        return completable.doOnSubscribe(this::add)
    }

    override fun dispose(): Unit = disposeLock.withLock {
        /* Get all currently registered disposables */
        val toDispose = disposablesLock.withLock(disposables::poll)

        /*
        Dispose all disposables and collect all that are still returning 'not disposed'.
        This objects signal that they might want to receive further dispose events
        e.g. Disposer objects
         */
        val notDisposed = toDispose.asSequence()
            .onEach(Disposable::dispose)
            .filterNot(Disposable::isDisposed)


        /* Re-Add all 'not disposed objects to the list of disposables */
        disposeLock.withLock {
            this.disposables.addAll(notDisposed)
        }
    }
}