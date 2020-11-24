package com.codecool.applicationa.product_screen

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.codecool.applicationa.MainActivity
import com.codecool.applicationa.R
import com.codecool.applicationa.database.DatabaseSingleton
import com.codecool.applicationa.database.PlantProduct
import com.codecool.applicationa.main_page.MainPageFragment.Companion.PRODUCT_ID
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_cart.*
import kotlinx.android.synthetic.main.fragment_product.*
import kotlinx.android.synthetic.main.fragment_product.back_button
import kotlinx.android.synthetic.main.fragment_product.log_out_button
import kotlinx.android.synthetic.main.fragment_product.product_image
import kotlinx.android.synthetic.main.fragment_product.product_price
import kotlinx.android.synthetic.main.fragment_product.product_name


class ProductFragment : Fragment(), ProductContractor {

    val presenter = ProductPresenter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onAttach(this)
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

                add_to_cart.setOnClickListener {
                    presenter.onItemAddToCart(product)
                }
            }
        } ?: run{
            findNavController().popBackStack()
        }

        log_out_button.setOnClickListener {

            DatabaseSingleton.getAuth().signOut()
            val intent = Intent(context, MainActivity::class.java)
            activity?.finish()
            startActivity(intent)
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