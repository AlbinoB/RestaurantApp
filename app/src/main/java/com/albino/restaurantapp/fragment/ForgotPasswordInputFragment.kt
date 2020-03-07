package com.albino.restaurantapp.fragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat

import com.albino.restaurantapp.R
import com.albino.restaurantapp.utils.ConnectionManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject


lateinit var editTextMobileNumber:EditText
lateinit var editTextEmail:EditText
lateinit var buttonNext:Button

class ForgotPasswordInputFragment(val contextParam: Context) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view=inflater.inflate(R.layout.fragment_forg, container, false)

        editTextMobileNumber=view.findViewById(R.id.editTextMobileNumber)
        editTextEmail=view.findViewById(R.id.editTextEmail)
        buttonNext=view.findViewById(R.id.buttonNext)

        buttonNext.setOnClickListener(View.OnClickListener {
            if (editTextMobileNumber.text.isBlank())
            {
                editTextMobileNumber.setError("Mobile Number Missing")
            }else{
                if(editTextEmail.text.isBlank()){
                    editTextEmail.setError("Email Missing")
                }else{

                    if (ConnectionManager().checkConnectivity(activity as Context)) {

                        try {

                            val loginUser = JSONObject()

                            loginUser.put("mobile_number", editTextMobileNumber.text)
                            loginUser.put("email", editTextEmail.text)



                            val queue = Volley.newRequestQueue(activity as Context)

                            val url = "http://13.235.250.119/v2/forgot_password/fetch_result"

                            val jsonObjectRequest = object : JsonObjectRequest(
                                Request.Method.POST,
                                url,
                                loginUser,
                                Response.Listener {
                                    println("Response12 is " + it)

                                    val responseJsonObjectData = it.getJSONObject("data")

                                    val success = responseJsonObjectData.getBoolean("success")

                                    if (success) {

                                        val first_try=responseJsonObjectData.getBoolean("first_try")

                                        if(first_try){
                                            Toast.makeText(
                                                contextParam,
                                                "OTP sent",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                            callChangePasswordFragment()//after we get a response we call the Log the user in
                                        }else{
                                            Toast.makeText(
                                                contextParam,
                                                "You have the exceeded reset tries",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }

                                    } else {
                                        val responseMessageServer =
                                            responseJsonObjectData.getString("errorMessage")
                                        Toast.makeText(
                                            contextParam,
                                            responseMessageServer.toString(),
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                },
                                Response.ErrorListener {
                                    println("Error12 is " + it)

                                    Toast.makeText(
                                        contextParam,
                                        "mSome Error occurred!!!",
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
                    }else
                    {
                        val alterDialog=androidx.appcompat.app.AlertDialog.Builder(activity as Context)

                        alterDialog.setTitle("No Internet")
                        alterDialog.setMessage("Internet Connection can't be establish!")
                        alterDialog.setPositiveButton("Open Settings"){text,listener->
                            val settingsIntent= Intent(Settings.ACTION_WIRELESS_SETTINGS)//open wifi settings
                            startActivity(settingsIntent)
                            activity?.finish()
                        }

                        alterDialog.setNegativeButton("Exit"){ text,listener->
                            ActivityCompat.finishAffinity(activity as Activity)//closes all the instances of the app and the app closes completely
                        }
                        alterDialog.create()
                        alterDialog.show()

                    }
                }
            }
        })

        return view
    }

    fun callChangePasswordFragment(){
        val transaction = fragmentManager?.beginTransaction()

        transaction?.replace(
            R.id.frameLayout,
            ForgotPasswordFragment(contextParam, editTextMobileNumber.text.toString())
        )//replace the old layout with the new frag  layout

        transaction?.commit()//apply changes
    }
}


