package com.codecool.applicationa.product_screen

import com.codecool.applicationa.database.PlantProduct
import com.codecool.applicationa.koin.database_cart.CartService
import com.codecool.applicationa.koin.serviceCallbacks
import org.koin.core.KoinComponent
import org.koin.core.inject

class ProductPresenter : KoinComponent {

    val cartService : CartService by inject()

    private var view : ProductContractor? = null

    fun onAttach ( view : ProductContractor){
        this.view = view
    }

    fun onDetach(){
        this.view = null
    }

    fun onItemAddToCart(product : PlantProduct){
        view?.let{ view ->
            cartService.addItemToCart(
                item = product,
                callback = object : serviceCallbacks.addItemToCartCallback{
                    override fun onCompleted() {
                        view.onItemAddCompleted()
                    }

                    override fun onError() {
                        view.onItemAddFailed()
                    }
                }
            )
        }
    }
}