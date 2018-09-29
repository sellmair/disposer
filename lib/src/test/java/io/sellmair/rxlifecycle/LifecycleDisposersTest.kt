package io.sellmair.rxlifecycle

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleRegistry
import io.reactivex.subjects.PublishSubject
import io.sellmair.rxlifecycle.internal.get
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class LifecycleDisposersTest {

    private lateinit var lifecycle: LifecycleRegistry


    @Before
    fun setup() {
        lifecycle = TestLifecycle.create()
    }

    @Test
    fun store_returnsSameInstance() {
        val instance1 = LifecycleDisposers.Store[lifecycle]
        val instance2 = LifecycleDisposers.Store[lifecycle]
        Assert.assertEquals(instance1, instance2)
    }

    @Test
    fun onStart_disposes() {
        val subject = PublishSubject.create<Unit>()
        val observer = TestObserver()

        subject
            .disposeBy(lifecycle.onStart)
            .subscribe(observer)

        subject.onNext(Unit)
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_START)
        subject.onNext(Unit)

        Assert.assertEquals(1, observer.nextCount)
    }

    @Test
    fun onStart_doesNotDispose() {
        val subject = PublishSubject.create<Unit>()
        val observer = TestObserver()

        subject
            .disposeBy(lifecycle.onStart)
            .subscribe(observer)

        subject.onNext(Unit)
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
        subject.onNext(Unit)

        Assert.assertEquals(2, observer.nextCount)
    }
}