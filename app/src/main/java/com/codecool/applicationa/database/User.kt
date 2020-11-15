package com.codecool.applicationa.database

class User() {

    private var username : String = ""
    private var emailAdress : String = ""
    private var userId : String = ""

    constructor(un : String, ea : String, ui : String) : this(){
        this.userId = ui
        this.username = un
        this.emailAdress = ea
    }
}