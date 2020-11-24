package com.codecool.applicationa.main_page

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.codecool.applicationa.MainActivity
import com.codecool.applicationa.R
import com.codecool.applicationa.database.DatabaseSingleton
import com.codecool.applicationa.database.PlantProduct
import com.codecool.applicationa.main_page.MainPageFragment.Companion.PRODUCT_ID
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_cart.*
import kotlinx.android.synthetic.main.fragment_main_page.*
import kotlinx.android.synthetic.main.fragment_main_page.log_out_button
import kotlinx.android.synthetic.main.grid_recycler_row.view.*
import java.util.*


class MainPageFragment : Fragment() {

    companion object{
        const val PRODUCT_ID = "com.codecool.plantapp.main_page.product_id"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecycler()
        go_to_cart.setOnClickListener { findNavController().navigate(R.id.action_mainPageFragment_to_cartFragment) }
        log_out_button.setOnClickListener {
            DatabaseSingleton.getAuth().signOut()
            val intent = Intent(context, MainActivity::class.java)
            activity?.finish()
            startActivity(intent)
        }
    }

    fun setUpRecycler(){
        context?.let{
            val adapter = ProductAdapter(PlantProduct.PRODUCT_LIST,layoutInflater,findNavController())
            recycler.adapter = adapter
            recycler.layoutManager = GridLayoutManagerFit(it)

            // We need to update the adapter every time the searchbar has a new character
            search_bar.addTextChangedListener {text ->
                if ( text.isNullOrBlank() || text.isNullOrEmpty() ){
                    adapter.updateList(PlantProduct.PRODUCT_LIST)
                } else {
                    val list = PlantProduct.PRODUCT_LIST.filter {product ->
                        text.toString().toLowerCase(Locale.ROOT) in product.productName.toLowerCase(
                            Locale.ROOT
                        )
                    }
                    adapter.updateList(list)
                }
            }
        }
    }

}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
class ProductAdapter(var list : List<PlantProduct>, val layoutInflater : LayoutInflater,val navController : NavController) : RecyclerView.Adapter<ViewHolder>(){

    fun updateList(list : List<PlantProduct>){
        this.list = list
        notifyItemRangeChanged(0,list.size)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = list[position]
        val view = holder.itemView

        view.product_name.text = product.productName
        view.product_price.text = "$${product.productPrice}"
        Picasso.get().load(product.imageLink).into(view.product_image)

        view.setOnClickListener{
            val bundle = Bundle()
            bundle.putInt(PRODUCT_ID,list.indexOf(product))
            navController.navigate(R.id.action_mainPageFragment_to_productFragment,bundle)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(layoutInflater.inflate(R.layout.grid_recycler_row,parent,false))
    }
}