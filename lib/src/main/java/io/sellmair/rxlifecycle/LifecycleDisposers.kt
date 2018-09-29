@file:Suppress("unused")

package io.sellmair.rxlifecycle

/*
################################################################################################
PUBLIC API
################################################################################################
*/
class LifecycleDisposers(
    val onCreate: Disposer,
    val onStart: Disposer,
    val onResume: Disposer,
    val onPause: Disposer,
    val onStop: Disposer,
    val onDestroy: Disposer) {

    /*
    ################################################################################################
    INTERNAL API
    ################################################################################################
    */
    internal object Factory
    internal object Store
}




