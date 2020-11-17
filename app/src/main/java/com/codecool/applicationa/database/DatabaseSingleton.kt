package com.codecool.applicationa.database

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class DatabaseSingleton {
    companion object{

        const val USERS_TABLE = "users"
        const val CART_TABLE = "cart"

        private var mDatabase : FirebaseDatabase? = null
        private var mAuth : FirebaseAuth? = null

        fun getDatabase() : FirebaseDatabase{
            if ( mDatabase == null){
                mDatabase = FirebaseDatabase.getInstance()
            }
            return mDatabase!!
        }

        fun getAuth() : FirebaseAuth{
            if (mAuth == null){
                mAuth = FirebaseAuth.getInstance()
            }
            return mAuth!!
        }
    }
}