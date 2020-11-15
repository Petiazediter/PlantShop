package com.codecool.applicationa.koin

import com.codecool.applicationa.koin.database_sign_up.KoinSignUp
import com.codecool.applicationa.koin.database_sign_up.SignUpService
import org.koin.dsl.module

val modules = module{
    single<SignUpService>{KoinSignUp()}
}