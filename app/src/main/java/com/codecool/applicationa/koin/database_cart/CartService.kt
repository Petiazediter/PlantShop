package com.codecool.applicationa.koin.database_cart

import com.codecool.applicationa.koin.serviceCallbacks

interface CartService {
    fun getUserCart( callback : serviceCallbacks.getUserCartCallback)
}