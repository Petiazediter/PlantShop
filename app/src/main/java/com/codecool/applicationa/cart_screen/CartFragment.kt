package com.codecool.applicationa.cart_screen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codecool.applicationa.MainActivity
import com.codecool.applicationa.R
import com.codecool.applicationa.database.CartItems
import com.codecool.applicationa.database.DatabaseSingleton
import com.codecool.applicationa.database.PlantProduct
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cart_row_layout.view.*
import kotlinx.android.synthetic.main.fragment_cart.*


class CartFragment : Fragment(), CartContractor {

    private val presenter = CartPresenter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onAttach(this)
        presenter.getCartItems()
        back_button.setOnClickListener {
            findNavController().popBackStack()
        }

        log_out_button.setOnClickListener {
            DatabaseSingleton.getAuth().signOut()
            val intent = Intent(context,MainActivity::class.java)
            activity?.finish()
            startActivity(intent)
        }
    }

    override fun onStop() {
        super.onStop()
        presenter.onDetach()
    }

    override fun onUpdateList(list : List<CartItems>){
        setTotalOrder(list)
    }

    @SuppressLint("SetTextI18n")
    override fun onComplete(list: List<CartItems>) {
        val mList = ArrayList<CartItems>(list)
        cart_recycler.adapter = CartRecyclerAdapter(layoutInflater, mList, object : RecyclerContractor{
            override fun onItemQuantityChanged(item: CartItems) {
                presenter.changeItemInCart(item)
            }

            override fun onItemDeleted(uId: String) {
                presenter.removeItemFromCart(uId)
            }

        })

        cart_recycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        setTotalOrder(list)
    }

    private fun setTotalOrder(list: List<CartItems>) {
        val productFullPrice = list.map {
            PlantProduct.PRODUCT_LIST[it.itemId].productPrice * it.quantity
        }.sum()
        order_total.text = resources.getString(R.string.order_total) + productFullPrice.toString()
    }
}

class CartRecyclerAdapter(
    private val layoutInflater: LayoutInflater,
    private val listOfItems: ArrayList<CartItems>, private val contractor: RecyclerContractor
) :
    RecyclerView.Adapter<CartRecyclerAdapter.ViewHolder>() {

    override fun getItemCount(): Int = listOfItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.dbItem = listOfItems[position]
        holder.product = PlantProduct.PRODUCT_LIST[holder.dbItem.itemId]
        holder.recyclerContractor = contractor
        holder.initial()
        holder.view.delete_button.setOnClickListener {
            contractor.onItemDeleted(holder.dbItem.uId)
            listOfItems.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position,listOfItems.size)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(layoutInflater.inflate(R.layout.cart_row_layout, parent, false))

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        lateinit var product : PlantProduct
        lateinit var dbItem : CartItems
        lateinit var recyclerContractor: RecyclerContractor

        fun initial(){
            renameProduct(product.productName)
            setProductQuantity(dbItem.quantity)
            loadImage(product.imageLink)
            addEditTextListener()
        }

        fun renameProduct(name: String) {
            view.product_name.text = name
        }

        private fun setProductPrice(quantity: Int) {
            view.product_price.text = "$${quantity * product.productPrice}"
        }

        fun setProductQuantity(quantity: Int) {
            view.quantity_et.setText(quantity.toString())
            setProductPrice(quantity)
        }

        fun loadImage(url: String) = Picasso.get().load(url).into(view.product_image)

        fun addEditTextListener() {
            view.quantity_et.addTextChangedListener {
                it?.let {
                    try {
                        setProductPrice( it.toString().toInt())
                        dbItem.quantity = it.toString().toInt()
                        recyclerContractor.onItemQuantityChanged(dbItem)
                    } catch (e: Exception) {
                        setProductQuantity(1)
                        dbItem.quantity = 1
                        recyclerContractor.onItemQuantityChanged(dbItem)
                    }
                }
            }
        }
    }
}