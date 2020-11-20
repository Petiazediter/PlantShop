package com.codecool.applicationa.cart_screen

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.codecool.applicationa.R
import com.codecool.applicationa.database.CartItems
import com.codecool.applicationa.database.PlantProduct
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cart_row_layout.view.*
import kotlinx.android.synthetic.main.fragment_cart.*
import kotlinx.android.synthetic.main.fragment_main_page.*
import java.lang.Exception


class CartFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}

class CartRecyclerAdapter( val layoutInflater: LayoutInflater, val listOfItems : List<CartItems>) : RecyclerView.Adapter<CartRecyclerAdapter.ViewHolder>(){

    override fun getItemCount(): Int = listOfItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = listOfItems[position]
        val item = PlantProduct.PRODUCT_LIST[product.itemId]

        holder.renameProduct(item.productName)
        holder.setProductQuantity(product.quantity,item.productPrice)
        holder.loadImage(item.imageLink)

        holder.addEditTextListener(item.productPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(layoutInflater.inflate(R.layout.cart_row_layout,parent,false))

    class ViewHolder( val view: View) : RecyclerView.ViewHolder(view) {

        fun renameProduct ( name : String) {
            view.product_name.text = name
        }

        private fun setProductPrice( price : Int, quantity: Int ){
            view.product_price.text = "$${quantity *price}"
        }

        fun setProductQuantity( quantity : Int, price: Int ){
            view.quantity_et.setText(quantity.toString())
            setProductPrice(price,quantity)
        }

        fun loadImage(url : String ) = Picasso.get().load(url).into(view.product_image)

        fun addEditTextListener(price : Int){
            view.quantity_et.addTextChangedListener{
                it?.let{
                    try{
                        setProductPrice(price,it.toString().toInt())
                    } catch ( e : Exception){
                        setProductQuantity( 1, price)
                    }
                }
            }
        }
    }
}