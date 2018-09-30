package io.sellmair.disposer

import io.reactivex.disposables.Disposables
import io.sellmair.disposer.internal.LockedDisposer
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class LockedDisposerTest {
    private lateinit var disposer: LockedDisposer

    @Before
    fun setup() {
        disposer = LockedDisposer()
    }

    @Test
    fun dispose_disposesSingleDisposable() {
        val disposable = Disposables.empty()
        disposer.add(disposable)

        assertFalse(disposable.isDisposed)
        disposer.dispose()
        assertTrue(disposable.isDisposed)
    }


    @Test
    fun dispose_disposesMultipleDisposables() {
        val disposables = Array(12) { Disposables.empty() }
        for (disposable in disposables) {
            disposer.add(disposable)
            assertFalse(disposable.isDisposed)
        }

        disposer.dispose()
        for (disposable in disposables) {
            assertTrue(disposable.isDisposed)
        }
    }


    @Test
    fun dispose_worksMultipleTimes() = repeat(10) {
        dispose_disposesMultipleDisposables()
    }

}