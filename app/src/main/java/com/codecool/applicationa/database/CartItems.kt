package com.codecool.applicationa.database

class CartItems() {
    var uId : String = ""
    var itemId : Int = 0

    constructor(uId : String, itemId : Int) : this(){
        this.uId = uId
        this.itemId = itemId
    }
}