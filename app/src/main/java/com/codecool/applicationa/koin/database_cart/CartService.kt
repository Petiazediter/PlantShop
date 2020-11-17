package com.codecool.applicationa.koin.database_cart

import com.codecool.applicationa.database.PlantProduct
import com.codecool.applicationa.koin.serviceCallbacks

interface CartService {
    fun getUserCart( callback : serviceCallbacks.getUserCartCallback)
    fun addItemToCart ( item : PlantProduct, callback: serviceCallbacks.addItemToCartCallback)
}