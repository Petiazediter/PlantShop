package com.codecool.applicationa.product_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.codecool.applicationa.R
import com.codecool.applicationa.database.PlantProduct
import com.codecool.applicationa.main_page.MainPageFragment.Companion.PRODUCT_ID
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_product.*
import kotlinx.android.synthetic.main.fragment_product.product_image
import kotlinx.android.synthetic.main.fragment_product.product_price
import kotlinx.android.synthetic.main.fragment_product.product_name


class ProductFragment : Fragment(), ProductContractor {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let{ args ->
            val id = args.getInt(PRODUCT_ID,-1)
            if ( id == -1){
                findNavController().popBackStack()
            } else {
                val product = PlantProduct.PRODUCT_LIST.get(id)
                product_description.text = product.productDescription
                product_price.text = "$${product.productPrice}"
                Picasso.get().load(product.imageLink).into(product_image)
                product_name.text = product.productName

                back_button.setOnClickListener{
                    findNavController().popBackStack()
                }


            }
        } ?: run{
            findNavController().popBackStack()
        }
    }

    override fun onItemAddCompleted() {
        context?.let{
            Toast.makeText(it,resources.getString(R.string.successful_cart_add),Toast.LENGTH_SHORT ).show()
        }
    }

    override fun onItemAddFailed() {
        context?.let{
            Toast.makeText(it,resources.getString(R.string.error_cart_add),Toast.LENGTH_LONG ).show()
        }
    }
}