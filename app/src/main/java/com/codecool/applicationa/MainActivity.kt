package com.codecool.applicationa

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.findNavController
import com.codecool.applicationa.database.DatabaseSingleton
import com.codecool.applicationa.logged_in_activity.LoggedInActivity
import com.codecool.applicationa.splash_screen.SplashActivity

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
        if ( result ){
            // If the app opened before
            // We need to check if the user is logged in already.
            DatabaseSingleton.getAuth()
                .currentUser?.let{
                    presenter.validateUser(it.uid) }
        } else {
            // If the app not opened before start the splash screen activity
            val splashScreenActivity = Intent(applicationContext,SplashActivity::class.java)
            startActivity(splashScreenActivity)
            finish()
        }
    }

    override fun isUserValid(result: Boolean) {
        if ( result ) {
            val loggedInActivity = Intent(applicationContext,LoggedInActivity::class.java)
            startActivity(loggedInActivity)
            finish()
        } else {
            DatabaseSingleton.getAuth()
                .signOut()
        }
    }
}