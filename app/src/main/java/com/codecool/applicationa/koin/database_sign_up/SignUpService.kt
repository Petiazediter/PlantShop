package com.codecool.applicationa.koin.database_sign_up

import com.codecool.applicationa.koin.serviceCallbacks

interface SignUpService {
    fun attemptRegister ( username : String, password : String, email : String, callback : serviceCallbacks.attemptRegisterCallback)
}

