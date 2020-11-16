package com.codecool.applicationa.sign_up_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.codecool.applicationa.R
import kotlinx.android.synthetic.main.fragment_sign_up.*


class SignUpFragment : Fragment(), SignUpContractor {

    private val presenter = SignUpPresenter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        to_login.setOnClickListener {
            findNavController()
                .navigate(R.id.action_signUpFragment_to_signInFragment)
        }

        presenter.onAttach(this)

        sign_up_button.setOnClickListener {
            val username = username.text.toString()
            val password1 = password.text.toString()
            val password2 = password2.text.toString()
            val email = email.text.toString()
            presenter.attemptRegister(username,password1,password2,email)
            sign_up_button.isEnabled = false
        }
    }


     // KNOWN BUG : The button stays disabled.
    override fun onError(messageId: Int) {
        sign_up_button.isEnabled = true
        context?.let{
            Toast.makeText(it,resources.getString(messageId),Toast.LENGTH_LONG)
                .show()
        }
    }

    override fun onSuccess() {
        sign_up_button.isEnabled = true
        context?.let{
            Toast.makeText(it,resources.getString(R.string.success_register),Toast.LENGTH_LONG)
                .show()
        }
        findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
    }
}