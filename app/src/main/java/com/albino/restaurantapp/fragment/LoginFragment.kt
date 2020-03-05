package com.albino.restaurantapp.fragment


import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

import com.albino.restaurantapp.R
import com.albino.restaurantapp.activity.Dashboard
import com.albino.restaurantapp.activity.LoginRegisterActivity

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment() {

    lateinit var textViewSignUp: TextView
    lateinit var editTextMobileNumber: EditText
    lateinit var editTextPassword: EditText
    lateinit var textViewForgotPassword: TextView
    lateinit var buttonLogin:Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        val view = inflater.inflate(R.layout.fragment_login, container, false)

        editTextMobileNumber=view.findViewById(R.id.editTextMobileNumber)
        editTextPassword=view.findViewById(R.id.editTextPassword)
        textViewForgotPassword=view.findViewById(R.id.textViewForgotPassword)
        textViewSignUp=view.findViewById(R.id.textViewSignUp)
        buttonLogin=view.findViewById(R.id.buttonLogin)



        textViewSignUp.setOnClickListener(View.OnClickListener {
            openRegisterFragment()
        })


        buttonLogin.setOnClickListener(View.OnClickListener {
            val intent=Intent(activity as Context,Dashboard::class.java)
            startActivity(intent)
            getActivity()?.finish();
        })





        return view
    }

    fun openRegisterFragment() {


        val fragment = RegisterFragment()
        val transaction = fragmentManager?.beginTransaction()

        transaction?.replace(
            R.id.frameLayout,
            RegisterFragment()
        )//replace the old layout with the new frag  layout

        transaction?.commit()//apply changes


    }


}
