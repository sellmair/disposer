package io.sellmair.rxlifecycle

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.reactivex.Observable

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    @SuppressLint("CheckResult")
    override fun onStart() {
        super.onStart()

        lifecycle.disposers.onStop += fetchData()
            .flatMap(::prepareData)
            .subscribe(::displayData)


        fetchData()
            .flatMap(::prepareData)
            .disposeBy(lifecycle.disposers.onStop)
            .subscribe(::displayData)


        fetchData()
            .flatMap(::prepareData)
            .subscribe(::displayData)
            .disposeBy(lifecycle.disposers.onStop)


    }

    fun displayData(unit: Unit) {

    }
}


fun fetchData(): Observable<Unit> {
    return Observable.just(Unit)
}

fun prepareData(unit: Unit): Observable<Unit> {
    return Observable.just(Unit)
}