package com.codecool.applicationa.koin

import com.codecool.applicationa.koin.database_cart.CartQueries
import com.codecool.applicationa.koin.database_cart.CartService
import com.codecool.applicationa.koin.database_sign_in.KoinSignIn
import com.codecool.applicationa.koin.database_sign_in.SignInService
import com.codecool.applicationa.koin.database_sign_up.KoinSignUp
import com.codecool.applicationa.koin.database_sign_up.SignUpService
import org.koin.dsl.module

val modules = module{
    single<SignUpService>{KoinSignUp()}
    single<SignInService>{KoinSignIn()}
    single<CartService>{CartQueries()}
}