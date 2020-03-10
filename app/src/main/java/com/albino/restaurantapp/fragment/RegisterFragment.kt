package com.albino.restaurantapp.fragment


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat

import com.albino.restaurantapp.R
import com.albino.restaurantapp.activity.Dashboard
import com.albino.restaurantapp.activity.toolbarOrderHistroy
import com.albino.restaurantapp.utils.ConnectionManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject

/**
 * A simple [Fragment] subclass.
 */
class RegisterFragment(val contextParam:Context) : Fragment() {

    lateinit var editTextName:EditText
    lateinit var editTextEmail:EditText
    lateinit var editTextMobileNumber:EditText
    lateinit var editTextDeliveryAddress:EditText
    lateinit var editTextPassword:EditText
    lateinit var editTextConfirmPassword:EditText
    lateinit var buttonRegister:Button
    lateinit var toolbar:Toolbar
    lateinit var register_fragment_Progressdialog:RelativeLayout


    var insertSuccessfully=false





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
        buttonRegister=view.findViewById(R.id.buttonSubmit)
        toolbar=view.findViewById(R.id.toolBar)
        register_fragment_Progressdialog=view.findViewById(R.id.register_fragment_Progressdialog)


        setToolBar()

        buttonRegister.setOnClickListener(View.OnClickListener {
            registerUserFun()
        })

        return view
    }


    fun userSuccessfullyRegistered(){
        openDashBoard()
    }

    fun openDashBoard() {

        val intent=Intent(activity as Context, Dashboard::class.java)
        startActivity(intent)
        getActivity()?.finish();

    }


    fun registerUserFun(){


        val sharedPreferencess=contextParam.getSharedPreferences(getString(R.string.shared_preferences),Context.MODE_PRIVATE)


        sharedPreferencess.edit().putBoolean("user_logged_in", false).commit()

        if (ConnectionManager().checkConnectivity(activity as Context)) {


            if (checkForErrors()){

                register_fragment_Progressdialog.visibility=View.VISIBLE
                try {

                    val registerUser = JSONObject()
                    registerUser.put("name", editTextName.text)
                    registerUser.put("mobile_number", editTextMobileNumber.text)
                    registerUser.put("password", editTextPassword.text)
                    registerUser.put("address", editTextDeliveryAddress.text)
                    registerUser.put("email", editTextEmail.text)


                    val queue = Volley.newRequestQueue(activity as Context)

                    val url = "http://13.235.250.119/v2/register/fetch_result"

                    val jsonObjectRequest = object : JsonObjectRequest(
                        Request.Method.POST,
                        url,
                        registerUser,
                        Response.Listener {
                            println("Response12 is " + it)

                            val responseJsonObjectData = it.getJSONObject("data")

                            val success = responseJsonObjectData.getBoolean("success")


                            if (success) {

                                val data = responseJsonObjectData.getJSONObject("data")
                                sharedPreferencess.edit().putBoolean("user_logged_in", true).commit()
                                sharedPreferencess.edit().putString("user_id", data.getString("user_id")).commit()
                                sharedPreferencess.edit().putString("name", data.getString("name")).apply()
                                sharedPreferencess.edit().putString("email", data.getString("email")).apply()
                                sharedPreferencess.edit().putString("mobile_number", data.getString("mobile_number")).apply()
                                sharedPreferencess.edit().putString("address", data.getString("address")).apply()


                                Toast.makeText(
                                    contextParam,
                                    "Registered sucessfully",
                                    Toast.LENGTH_SHORT
                                ).show()

                                userSuccessfullyRegistered()//after we get a response we call the login


                            } else {
                                val responseMessageServer =
                                    responseJsonObjectData.getString("errorMessage")
                                Toast.makeText(
                                    contextParam,
                                    responseMessageServer.toString(),
                                    Toast.LENGTH_SHORT
                                ).show()

                            }
                            register_fragment_Progressdialog.visibility=View.INVISIBLE
                        },
                        Response.ErrorListener {
                            println("Error12 is " + it)
                            register_fragment_Progressdialog.visibility=View.INVISIBLE

                            Toast.makeText(
                                contextParam,
                                "Some Error occurred!!!",
                                Toast.LENGTH_SHORT
                            ).show()



                        }) {
                        override fun getHeaders(): MutableMap<String, String> {
                            val headers = HashMap<String, String>()

                            headers["Content-type"] = "application/json"
                            headers["token"] = "acdc385cfd7264"

                            return headers
                        }
                    }

                    queue.add(jsonObjectRequest)

                } catch (e: JSONException) {
                    Toast.makeText(
                        contextParam,
                        "Some unexpected error occured!!!",
                        Toast.LENGTH_SHORT
                    ).show()

                }

            }

        }else
        {
            val alterDialog=androidx.appcompat.app.AlertDialog.Builder(activity as Context)

            alterDialog.setTitle("No Internet")
            alterDialog.setMessage("Internet Connection can't be establish!")
            alterDialog.setPositiveButton("Open Settings"){text,listener->
                val settingsIntent= Intent(Settings.ACTION_SETTINGS)//open wifi settings
                startActivity(settingsIntent)

            }

            alterDialog.setNegativeButton("Exit"){ text,listener->
                ActivityCompat.finishAffinity(activity as Activity)//closes all the instances of the app and the app closes completely
            }
            alterDialog.create()
            alterDialog.show()

        }
    }

    fun checkForErrors():Boolean{
        var errorPassCount=0
        if(editTextName.text.isBlank()) {

            editTextName.setError("Field Missing!")
        }else{
            errorPassCount++
        }

        if(editTextMobileNumber.text.isBlank()){
            editTextMobileNumber.setError("Field Missing!")
        }else{
            errorPassCount++
        }

        if(editTextEmail.text.isBlank()){
            editTextEmail.setError("Field Missing!")
        }else{
            errorPassCount++
        }

        if(editTextDeliveryAddress.text.isBlank()){
            editTextDeliveryAddress.setError("Field Missing!")
        }else{
            errorPassCount++
        }

        if(editTextConfirmPassword.text.isBlank()){
            editTextConfirmPassword.setError("Field Missing!")
        }else{
            errorPassCount++
        }

        if(editTextPassword.text.isBlank()){
            editTextPassword.setError("Field Missing!")
        }else{
            errorPassCount++
        }

        if(editTextPassword.text.toString().toInt()==editTextConfirmPassword.text.toString().toInt()){
            errorPassCount++
        }else{
            editTextConfirmPassword.setError("Password don't match")
        }


        if(errorPassCount==7)
            return true
        else
            return false

    }

    fun setToolBar(){

        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.title="Register Yourself"
        (activity as AppCompatActivity).supportActionBar?.setHomeButtonEnabled(true)//enables the button on the tool bar
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)//displays the icon on the button
        (activity as AppCompatActivity).supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back_arrow)//change icon to custom
    }



    override fun onResume() {

        if (!ConnectionManager().checkConnectivity(activity as Context)) {

            val alterDialog=androidx.appcompat.app.AlertDialog.Builder(activity as Context)
            alterDialog.setTitle("No Internet")
            alterDialog.setMessage("Internet Connection can't be establish!")
            alterDialog.setPositiveButton("Open Settings"){text,listener->
                val settingsIntent= Intent(Settings.ACTION_SETTINGS)//open wifi settings
                startActivity(settingsIntent)
            }

            alterDialog.setNegativeButton("Exit"){ text,listener->
                ActivityCompat.finishAffinity(activity as Activity)//closes all the instances of the app and the app closes completely
            }
            alterDialog.setCancelable(false)

            alterDialog.create()
            alterDialog.show()

        }

        super.onResume()
    }



}
