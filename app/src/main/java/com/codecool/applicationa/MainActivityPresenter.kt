package com.codecool.applicationa

import android.content.SharedPreferences

class MainActivityPresenter {

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
        if ( !result) {
         //   sharedPreferences.edit()
         //       .putBoolean(IS_APP_OPENED_SOMEWHEN,true)
         //       .apply()
        }
        view?.isAppOpenedReciever(result)
    }
}