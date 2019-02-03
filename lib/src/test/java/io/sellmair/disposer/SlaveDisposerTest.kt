package io.sellmair.disposer

import io.reactivex.disposables.Disposables
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class SlaveDisposerTest {
    @Test
    fun `slave disposer is disposed when parent is disposed`() {
        val master = Disposer()
        val slave = Disposer(master)

        val probe = Disposables.empty()
        slave += probe
        master.dispose()

        assertTrue(probe.isDisposed)
    }


    @Test
    fun `slave disposer is always disposed when parent is disposed`() {
        val master = Disposer()
        val slave = Disposer(master)

        repeat(10) {
            val probe = Disposables.empty()
            slave += probe
            assertFalse(probe.isDisposed)

            master.dispose()
            assertTrue(probe.isDisposed)
        }
    }
}