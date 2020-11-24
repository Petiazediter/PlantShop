package com.codecool.applicationa.sign_up_screen

interface SignUpContractor {
    fun onError(messageId : Int)
    fun onSuccess()
}