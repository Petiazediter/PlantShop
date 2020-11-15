package com.codecool.applicationa.sign_in_screen

interface SignInContractor {
    fun onError(messageId : Int)
    fun onSuccess()
}