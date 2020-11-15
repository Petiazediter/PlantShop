package com.codecool.applicationa.koin.database_sign_up

import com.codecool.applicationa.database.DatabaseSingleton
import com.codecool.applicationa.database.User
import com.google.firebase.database.*
import org.koin.core.KoinComponent

class KoinSignUp : KoinComponent, SignUpService {

    override fun attemptRegister(
        username: String,
        password: String,
        email: String,
        callback: signUpCallbacks.attemptRegisterCallback
    ) {

        val userTable = DatabaseSingleton.getDatabase()
            .getReference(DatabaseSingleton.USERS_TABLE)

        userTable.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                // If the usertable is empty then no need further check
                if ( !snapshot.exists() || snapshot.children.toList().isNullOrEmpty()){
                    val user = User(un = username, ea = email, ui = "")
                    registerUserDatas(user,password,userTable,callback)
                } else{
                    // Check if there's a user with the same username or email adress
                    for ( data in snapshot.children){
                        val user = data.getValue(User::class.java)
                        user?.let{
                            if ( user.username == username || user.emailAdress == email){
                                callback.onError()
                                return
                            }
                        }
                    }

                    val user = User(username,email,"")
                    registerUserDatas(user,password,userTable,callback)
                }

            }

            override fun onCancelled(error: DatabaseError) {
                callback.onError()
            }
        })
    }

    private fun registerUserDatas(user : User, password: String,databaseReference: DatabaseReference, callback : signUpCallbacks.attemptRegisterCallback) {
        DatabaseSingleton.getAuth()
            .createUserWithEmailAndPassword(user.emailAdress,password)
            .addOnCompleteListener{
                it.addOnSuccessListener {
                    it.user?.let{mUser ->
                    val id = mUser.uid
                    databaseReference.child(id)
                        .setValue(user::class.java)
                    }
                    callback.onSuccess()
                }

                it.addOnCanceledListener {
                    callback.onError()
                }
            }
    }
}