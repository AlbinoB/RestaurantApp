package com.albino.restaurantapp.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.widget.Toolbar

import com.albino.restaurantapp.R

/**
 * A simple [Fragment] subclass.
 */
class RegisterFragment : Fragment() {

    lateinit var editTextName:EditText
    lateinit var editTextEmail:EditText
    lateinit var editTextMobileNumber:EditText
    lateinit var editTextDeliveryAddress:EditText
    lateinit var editTextPassword:EditText
    lateinit var editTextConfirmPassword:EditText
    lateinit var buttonRegister:Button
    lateinit var toolbar:Toolbar



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_register, container, false)

        editTextName=view.findViewById(R.id.editTextName)
        editTextEmail=view.findViewById(R.id.editTextEmail)
        editTextMobileNumber=view.findViewById(R.id.editTextMobileNumber)
        editTextDeliveryAddress=view.findViewById(R.id.editTextDeliveryAddress)
        editTextPassword=view.findViewById(R.id.editTextPassword)
        editTextConfirmPassword=view.findViewById(R.id.editTextConfirmPassword)
        buttonRegister=view.findViewById(R.id.buttonRegister)
        toolbar=view.findViewById(R.id.toolBar)



        buttonRegister.setOnClickListener(View.OnClickListener {
            openLoginFragment()// if saved to database success then call login
        })

        return view
    }

    fun openLoginFragment() {



        val transaction = fragmentManager?.beginTransaction()

        transaction?.replace(
            R.id.frameLayout,
            LoginFragment()
        )//replace the old layout with the new frag  layout

        transaction?.commit()//apply changes


    }






}
