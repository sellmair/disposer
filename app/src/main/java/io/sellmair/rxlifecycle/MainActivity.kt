package io.sellmair.rxlifecycle

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.reactivex.Observable

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Observable.just(true)
            .subscribe()
            .disposeBy(onDestroy)

    }
}
