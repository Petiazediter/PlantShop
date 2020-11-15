package com.codecool.applicationa.koin.database_sign_up

import org.koin.core.KoinComponent

class KoinSignUp : KoinComponent, SignUpService {

    override fun attemptRegister(
        username: String,
        password: String,
        email: String,
        callback: signUpCallbacks.attemptRegisterCallback
    ) {



    }
}