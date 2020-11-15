package com.codecool.applicationa.koin.database_sign_in

import com.codecool.applicationa.koin.serviceCallbacks

interface SignInService {
    fun attemptSignIn ( username : String, password : String, callback : serviceCallbacks.attemptRegisterCallback)
}