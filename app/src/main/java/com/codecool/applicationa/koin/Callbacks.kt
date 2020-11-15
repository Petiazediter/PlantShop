package com.codecool.applicationa.koin

object serviceCallbacks{

    interface attemptRegisterCallback{
        fun onSuccess()
        fun onError()
    }

    interface validateUserCallback{
        fun callback(result : Boolean)
    }
}