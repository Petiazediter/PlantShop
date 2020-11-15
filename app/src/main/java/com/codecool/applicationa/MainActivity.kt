package com.codecool.applicationa

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity(), MainActivityPresenterView {

    companion object{
        const val APP_SHARED_PREFERENCE = "com.codecool.plantshop.sharedpreferences"
    }
    private val presenter = MainActivityPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.onAttachView(view = this)

        // Check if the app is opened already
        presenter.isAppOpenedSomewhen(applicationContext.getSharedPreferences(APP_SHARED_PREFERENCE,
            Context.MODE_PRIVATE))
    }

    override fun onStop() {
        super.onStop()
        presenter.onDetachView()
    }

    override fun isAppOpenedReciever(result: Boolean) {

    }
}