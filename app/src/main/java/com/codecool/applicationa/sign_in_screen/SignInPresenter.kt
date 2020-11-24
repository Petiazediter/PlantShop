package com.codecool.applicationa.sign_in_screen
import com.codecool.applicationa.R
import com.codecool.applicationa.koin.database_cart.CartService
import com.codecool.applicationa.koin.database_sign_in.SignInService
import com.codecool.applicationa.koin.serviceCallbacks
import org.koin.core.KoinComponent
import org.koin.core.inject

class SignInPresenter : KoinComponent {

    val signInService : SignInService by inject()


    var view : SignInContractor? = null

    fun onAttach(v : SignInContractor){
        view = v
    }

    fun onDetach(){
        view = null
    }

    fun attemptSignIn(username : String,password : String){
        signInService.attemptSignIn(
            username = username,
            password = password,
            callback = object : serviceCallbacks.attemptRegisterCallback{
            override fun onError() {
                view?.onError(R.string.error_sign_in)
            }

            override fun onSuccess() {
                view?.onSuccess()
            }
        })
    }
}