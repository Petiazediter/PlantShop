package com.codecool.applicationa.database

class User() {

     var username : String = ""
     var emailAdress : String = ""
     var userId : String = ""

    constructor(un : String, ea : String, ui : String) : this(){
        this.userId = ui
        this.username = un
        this.emailAdress = ea
    }
}