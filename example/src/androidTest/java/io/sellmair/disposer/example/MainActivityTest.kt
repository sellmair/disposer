package io.sellmair.disposer.example

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.launchActivity
import org.junit.Test


class MainActivityTest {

    @Test
    fun startApp() {
        val scenario = launchActivity<MainActivity>()
        scenario.moveToState(Lifecycle.State.RESUMED)
        scenario.moveToState(Lifecycle.State.DESTROYED)
    }
}
