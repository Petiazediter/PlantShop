package com.codecool.applicationa.koin.database_sign_up

interface SignUpService {
    fun attemptRegister ( username : String, password : String, email : String, callback : signUpCallbacks.attemptRegisterCallback)
}

object signUpCallbacks{

    interface attemptRegisterCallback{
        fun onSuccess()
        fun onError()
    }

}