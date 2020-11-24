package com.codecool.applicationa.sign_in_screen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.codecool.applicationa.R
import com.codecool.applicationa.logged_in_activity.LoggedInActivity
import kotlinx.android.synthetic.main.fragment_sign_in.*


class SignInFragment : Fragment(), SignInContractor{

    val presenter = SignInPresenter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        to_register.setOnClickListener {
            findNavController()
                .navigate(R.id.action_signInFragment_to_signUpFragment)
        }

        presenter.onAttach(this)
        sign_in_button.setOnClickListener {
            val username = username.text.toString()
            val password = password.text.toString()
            presenter.attemptSignIn(username,password)
            sign_in_button.isEnabled = false
        }
    }


    override fun onError(messageId: Int) {
        sign_in_button.isEnabled = true
        context?.let{
            Toast.makeText(it, resources.getString(messageId),Toast.LENGTH_LONG)
                .show()
        }
    }

    override fun onSuccess() {
        sign_in_button.isEnabled = true
        context?.let{
            Toast.makeText(it, resources.getString(R.string.success_sign_in),Toast.LENGTH_LONG)
                .show()
        }

        activity?.let{ activity ->
            context?.let{context ->
                val intent = Intent(context,LoggedInActivity::class.java)
                startActivity(intent)
                activity.finish()
            }
        }
    }
}