package com.codecool.applicationa.koin.database_cart

import android.provider.ContactsContract
import com.codecool.applicationa.database.CartItems
import com.codecool.applicationa.database.DatabaseSingleton
import com.codecool.applicationa.koin.serviceCallbacks
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class CartQueries : CartService {
    override fun getUserCart( callback : serviceCallbacks.getUserCartCallback ) {
        val uId = DatabaseSingleton.getAuth().currentUser?.let{ firebaseUser ->

            // DB:
            //  L-> carts :
            //         L-> userId :
            //                L-> item1
            //                L-> item2
            val userCart = DatabaseSingleton.getDatabase().getReference(DatabaseSingleton.CART_TABLE)
                .child(firebaseUser.uid)

            userCart.addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onCancelled(error: DatabaseError) {
                    callback.onCompleted(listOf())
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    val cart = ArrayList<CartItems>()
                    if ( snapshot.exists() ){
                        for (data in snapshot.children){
                            data.getValue(CartItems::class.java)?.let{
                                cart.add ( it )
                            }
                        }
                    }
                    callback.onCompleted(cart)
                }
            })
        }
    }
}