package com.codecool.applicationa.koin

import com.codecool.applicationa.database.CartItems

object serviceCallbacks{

    interface attemptRegisterCallback{
        fun onSuccess()
        fun onError()
    }

    interface validateUserCallback{
        fun callback(result : Boolean)
    }

    interface getUserCartCallback{
        fun onCompleted( cartItems : List<CartItems> )
    }

    interface addItemToCartCallback {
        fun onCompleted()
        fun onError()
    }
}