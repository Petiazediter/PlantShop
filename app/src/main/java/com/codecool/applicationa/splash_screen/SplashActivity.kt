package com.codecool.applicationa.splash_screen

import android.os.Bundle
import android.support.wearable.activity.WearableActivity

class SplashActivity : WearableActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Enables Always-on
        setAmbientEnabled()
    }
}