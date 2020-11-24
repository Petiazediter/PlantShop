package com.codecool.applicationa.cart_screen

import com.codecool.applicationa.database.CartItems

interface RecyclerContractor {
    fun onItemQuantityChanged( item : CartItems)
    fun onItemDeleted ( uId: String)
}