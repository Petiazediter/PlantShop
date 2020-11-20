package com.codecool.applicationa.cart_screen

interface RecyclerContractor {
    fun onItemQuantityChanged( uId : String, quantity : Int )
    fun onItemDeleted ( uId: String)
}