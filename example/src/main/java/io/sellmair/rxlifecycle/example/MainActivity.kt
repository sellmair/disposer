package io.sellmair.rxlifecycle.example

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import io.sellmair.rxlifecycle.disposeBy
import io.sellmair.rxlifecycle.disposers
import io.sellmair.rxlifecycle.onStop

class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.text_view)
    }

    override fun onStart() {
        super.onStart()

        // Example 1
        DummyNetwork.query()
            .subscribe(::displayResponse)
            .disposeBy(onStop)

        // Example 1 (Verbose)
        DummyNetwork.query()
            .subscribe(::displayResponse)
            .disposeBy(lifecycle.disposers.onStop)


        // Example 2
        onStop += DummyNetwork.query()
            .subscribe(::displayResponse)

        // Example 2 (Verbose)
        lifecycle.disposers.onStop += DummyNetwork.query()
            .subscribe(::displayResponse)

    }

    private fun displayResponse(response: String) {
        textView.text = response
    }
}
