package com.codecool.applicationa.main_page

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codecool.applicationa.R
import com.codecool.applicationa.database.PlantProduct
import kotlinx.android.synthetic.main.fragment_main_page.*
import kotlinx.android.synthetic.main.grid_recycler_row.view.*


class MainPageFragment : Fragment() {

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
    }

    fun setUpRecycler(){
        context?.let{
            recycler.adapter = ProductAdapter(PlantProduct.PRODUCT_LIST,layoutInflater)
            recycler.layoutManager = GridLayoutManagerFit(it)
        }
    }

}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
class ProductAdapter(val list : List<PlantProduct>, val layoutInflater : LayoutInflater) : RecyclerView.Adapter<ViewHolder>(){

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = list[position]
        val view = holder.itemView

        view.product_name.text = product.productName
        view.product_price.text = "$${product.productPrice}"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(layoutInflater.inflate(R.layout.grid_recycler_row,parent,false))
    }
}