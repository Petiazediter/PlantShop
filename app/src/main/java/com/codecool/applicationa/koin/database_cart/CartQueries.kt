package com.codecool.applicationa.koin.database_cart

import android.provider.ContactsContract
import com.codecool.applicationa.database.CartItems
import com.codecool.applicationa.database.DatabaseSingleton
import com.codecool.applicationa.database.PlantProduct
import com.codecool.applicationa.koin.serviceCallbacks
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class CartQueries : CartService {
    override fun getUserCart( callback : serviceCallbacks.getUserCartCallback ) {
        DatabaseSingleton.getAuth().currentUser?.let{ firebaseUser ->

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

    override fun addItemToCart(
        item: PlantProduct,
        callback: serviceCallbacks.addItemToCartCallback
    ) {

        DatabaseSingleton.getAuth().currentUser?.let{ firebaseUser ->
            // If there's an item with the same id then just increase quantity
            val userCart = DatabaseSingleton.getDatabase().getReference(DatabaseSingleton.CART_TABLE).child(firebaseUser.uid)
            userCart.addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onCancelled(error: DatabaseError) {
                    callback.onError()
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    if ( snapshot.exists() ){
                        for ( data in snapshot.children){
                            data.getValue(CartItems::class.java)?.let{
                                if ( it.itemId == PlantProduct.PRODUCT_LIST.indexOf(item)){
                                    increaseQuantityOfProduct(firebaseUser.uid,data.key.toString(),it,callback)
                                    return
                                }
                            }
                        }
                    }
                    addNewItemToCart(firebaseUser.uid, item,callback)
                }
            })
        }
    }

    private fun increaseQuantityOfProduct(userId : String,uId: String,product: CartItems ,callback: serviceCallbacks.addItemToCartCallback) {
        val userCart = DatabaseSingleton.getDatabase().getReference(DatabaseSingleton.CART_TABLE).child(userId).child(uId)
        product.quantity ++
        userCart.setValue(product)
        callback.onCompleted()
    }

    private fun addNewItemToCart(userId: String, item: PlantProduct, callback: serviceCallbacks.addItemToCartCallback) {
        val cartItem = CartItems(
            itemId = PlantProduct.PRODUCT_LIST.indexOf(item),
            uId = "",
            quantity = 1)

        val uId = DatabaseSingleton.getDatabase().getReference(DatabaseSingleton.CART_TABLE).child(userId)
            .push().key.toString()

        cartItem.uId = uId

        DatabaseSingleton.getDatabase().getReference(DatabaseSingleton.CART_TABLE).child(userId)
            .child(uId).setValue(cartItem)

        callback.onCompleted()
    }
}