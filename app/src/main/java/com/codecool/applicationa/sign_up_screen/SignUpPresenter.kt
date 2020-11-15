package com.codecool.applicationa.sign_up_screen

import android.util.Patterns
import com.codecool.applicationa.R
import com.codecool.applicationa.koin.database_sign_up.SignUpService
import com.codecool.applicationa.koin.serviceCallbacks
import org.koin.core.KoinComponent
import org.koin.core.inject

class SignUpPresenter : KoinComponent {

    val signUpService : SignUpService by inject()

    private var view : SignUpContractor? = null

    fun onAttach(v : SignUpContractor){
        this.view = v
    }

    fun onDeatch(){
        this.view = null
    }

    fun attemptRegister(username : String, password : String, password2 : String, email : String){
        view?.let{fragment ->
            if ( username.length < 5){
                return fragment.onError(R.string.username_short)
            } else if ( password.length < 5){
                return fragment.onError(R.string.password_short)
            } else if ( password != password2){
                return fragment.onError(R.string.password_match)
            } else if ( !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                return fragment.onError(R.string.email_format)
            }

            signUpService.attemptRegister(
                username = username,
                password = password,
                email = email,
                callback = object : serviceCallbacks.attemptRegisterCallback{
                    override fun onSuccess() {
                        fragment.onSuccess()
                    }

                    override fun onError() {
                        fragment.onError(R.string.error_sign_up)
                    }
                }
            )
        }
    }
}