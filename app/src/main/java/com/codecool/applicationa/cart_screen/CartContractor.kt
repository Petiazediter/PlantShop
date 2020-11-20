package com.codecool.applicationa.cart_screen

import com.codecool.applicationa.database.CartItems

interface CartContractor {
    fun onComplete(list : List<CartItems>)
}