package com.codecool.applicationa.cart_screen

import com.codecool.applicationa.database.CartItems
import com.codecool.applicationa.koin.database_cart.CartService
import com.codecool.applicationa.koin.serviceCallbacks
import org.koin.core.KoinComponent
import org.koin.core.inject

class CartPresenter : KoinComponent {

    val cartService : CartService by inject()

    var view : CartContractor? = null

    fun onAttach(v : CartContractor){
        view = v
    }

    fun onDetach ( ) {
        view = null
    }

    fun getCartItems(){
        cartService.getUserCart(
            callback = object : serviceCallbacks.getUserCartCallback{
                override fun onCompleted(cartItems: List<CartItems>) {
                    view?.onComplete(cartItems)
                }
            }
        )
    }

    fun removeItemFromCart(uId : String){
        cartService.deleteItemFromCart(uId)
        updateTotalOrder()
    }

    fun changeItemInCart(item : CartItems){
        cartService.setItemQuantity(item)
        updateTotalOrder()
    }

    fun updateTotalOrder(){
        cartService.getUserCart(
            callback = object : serviceCallbacks.getUserCartCallback{
                override fun onCompleted(cartItems: List<CartItems>) {
                    view?.onUpdateList(cartItems)
                }
            }
        )
    }
}