package com.codecool.applicationa.database

class CartItems() {
    var uId : String = ""
    var itemId : Int = 0
    var quantity : Int = 0

    constructor(uId : String, itemId : Int, quantity : Int) : this(){
        this.uId = uId
        this.itemId = itemId
        this.quantity = quantity
    }
}