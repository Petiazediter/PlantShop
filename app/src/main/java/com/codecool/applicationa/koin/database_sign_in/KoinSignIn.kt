package com.codecool.applicationa.koin.database_sign_in

import android.renderscript.Sampler
import com.codecool.applicationa.database.DatabaseSingleton
import com.codecool.applicationa.database.User
import com.codecool.applicationa.koin.serviceCallbacks
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class KoinSignIn : SignInService{

    override fun attemptSignIn(
        username: String,
        password: String,
        callback: serviceCallbacks.attemptRegisterCallback
    ) {

        val userTable = DatabaseSingleton.getDatabase()
            .getReference(DatabaseSingleton.USERS_TABLE)

        userTable.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                callback.onError()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if ( !snapshot.exists() || snapshot.children.toList().isNullOrEmpty()){
                    callback.onError()
                } else {
                    for ( data in snapshot.children){
                        data.getValue(User::class.java)?.let{
                            if ( it.username == username){
                                signIn(it.emailAdress,password,callback)
                                return
                            }
                        }
                    }
                    callback.onError()
                }
            }
        })
    }

    private fun signIn(email : String,password : String,callback: serviceCallbacks.attemptRegisterCallback){
        DatabaseSingleton.getAuth().signInWithEmailAndPassword(email,password)
            .addOnCompleteListener {
                it.addOnSuccessListener {
                    callback.onSuccess()
                }

                it.addOnCanceledListener {
                    callback.onError()
                }
            }
    }


    override fun validateUser(userId: String, callback: serviceCallbacks.validateUserCallback) {
        DatabaseSingleton.getDatabase().getReference(DatabaseSingleton.USERS_TABLE)
            .addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onCancelled(error: DatabaseError) {
                    callback.callback(false)
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    if ( !snapshot.exists() || snapshot.children.toList().isNullOrEmpty()){
                        callback.callback(false)
                    }else{
                        // If there's a user with the same id then the user is valid!
                        callback.callback( snapshot.children.count{ (it.key == userId)} > 0)
                    }
                }
            })
    }
}