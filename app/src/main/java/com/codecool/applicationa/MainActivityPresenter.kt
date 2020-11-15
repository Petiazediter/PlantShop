package com.codecool.applicationa

import android.content.SharedPreferences
import android.util.Log
import com.codecool.applicationa.database.DatabaseSingleton
import com.codecool.applicationa.koin.database_sign_in.SignInService
import com.codecool.applicationa.koin.serviceCallbacks
import com.google.firebase.auth.FirebaseAuth
import org.koin.core.KoinComponent
import org.koin.core.inject

class MainActivityPresenter : KoinComponent {

    val signInService : SignInService by inject()

    companion object {
        private const val IS_APP_OPENED_SOMEWHEN : String = "com.codecool.plantapp.is_app_opened"
    }

    private var view : MainActivityPresenterView? = null

    fun onAttachView(view : MainActivityPresenterView){
        this.view = view
    }

    fun onDetachView (){
        this.view = null
    }

    fun isAppOpenedSomewhen(sharedPreferences: SharedPreferences) {
        val result = sharedPreferences.getBoolean(IS_APP_OPENED_SOMEWHEN,false)
        Log.d("MainActivityPresenter", "result -> $result" )
        if ( !result) {
           sharedPreferences.edit()
                .putBoolean(IS_APP_OPENED_SOMEWHEN,true)
               .apply()
        }
        view?.isAppOpenedReciever(result)
    }

    fun validateUser(userId : String){
        signInService.validateUser(userId,object : serviceCallbacks.validateUserCallback{
            override fun callback(result: Boolean) {
                view?.isUserValid(result)
            }
        })
    }
}