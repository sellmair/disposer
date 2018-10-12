package io.sellmair.disposer

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleRegistry
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class LifecycleDisposersTest {

    private lateinit var lifecycle: LifecycleRegistry
    private lateinit var observer: TestObserver
    private lateinit var subject: Subject<Unit>
    private lateinit var observable: Observable<Unit>


    @Before
    fun setup() {
        lifecycle = TestLifecycle.create()
        observer = TestObserver()
        subject = PublishSubject.create()
        observable = subject.map { Unit }
    }

    @Test
    fun subscribeOnResume_disposeOnPause() {
        lifecycle.markState(Lifecycle.State.RESUMED)
        val disposable = observable.subscribe(observer).disposeBy(lifecycle.onPause)
        assertFalse(disposable.isDisposed)

        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        assertTrue(disposable.isDisposed)
    }

    @Test
    fun subscribeOnStart_disposeOnStop() {
        lifecycle.markState(Lifecycle.State.STARTED)
        val disposable = observable.subscribe(observer).disposeBy(lifecycle.onStop)
        assertFalse(disposable.isDisposed)

        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_STOP)
        assertTrue(disposable.isDisposed)
    }

    @Test
    fun subscribeOnCreate_disposeOnDestroy() {
        lifecycle.markState(Lifecycle.State.CREATED)
        val disposable = observable.subscribe(observer).disposeBy(lifecycle.onDestroy)
        assertFalse(disposable.isDisposed)

        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        assertTrue(disposable.isDisposed)
    }
}