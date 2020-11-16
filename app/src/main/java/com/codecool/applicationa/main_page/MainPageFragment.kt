package com.codecool.applicationa.main_page

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.codecool.applicationa.R
import com.codecool.applicationa.database.PlantProduct
import com.codecool.applicationa.main_page.MainPageFragment.Companion.PRODUCT_ID
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_main_page.*
import kotlinx.android.synthetic.main.grid_recycler_row.view.*


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
    }

    fun setUpRecycler(){
        context?.let{
            recycler.adapter = ProductAdapter(PlantProduct.PRODUCT_LIST,layoutInflater,findNavController())
            recycler.layoutManager = GridLayoutManagerFit(it)
        }
    }

}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
class ProductAdapter(val list : List<PlantProduct>, val layoutInflater : LayoutInflater,val navController : NavController) : RecyclerView.Adapter<ViewHolder>(){

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