package com.albino.restaurantapp.fragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RelativeLayout
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
lateinit var forgot_password_input_fragment_Progressdialog:RelativeLayout

class ForgotPasswordInputFragment(val contextParam: Context) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view=inflater.inflate(R.layout.fragment_forgot_password_input_num_email, container, false)

        editTextMobileNumber=view.findViewById(R.id.editTextMobileNumber)
        editTextEmail=view.findViewById(R.id.editTextEmail)
        buttonNext=view.findViewById(R.id.buttonNext)
        forgot_password_input_fragment_Progressdialog=view.findViewById(R.id.forgot_password_input_fragment_Progressdialog)

        buttonNext.setOnClickListener(View.OnClickListener {

            println("inside click listener next")

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

                            println(loginUser.getString("mobile_number"))
                            println(loginUser.getString("email"))



                            val queue = Volley.newRequestQueue(activity as Context)

                            val url = "http://"+getString(R.string.ip_address)+"/v2/forgot_password/fetch_result"

                            forgot_password_input_fragment_Progressdialog.visibility=View.VISIBLE

                            val jsonObjectRequest = object : JsonObjectRequest(
                                Request.Method.POST,
                                url,
                                loginUser,
                                Response.Listener {

                                    val responseJsonObjectData = it.getJSONObject("data")

                                    val success = responseJsonObjectData.getBoolean("success")

                                    if (success) {

                                        val first_try=responseJsonObjectData.getBoolean("first_try")

                                        if(first_try==true){
                                            Toast.makeText(
                                                contextParam,
                                                "OTP sent",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                            callChangePasswordFragment()//after we get a response we call the Log the user in
                                        }else{
                                            Toast.makeText(
                                                contextParam,
                                                "OTP sent already",
                                                Toast.LENGTH_SHORT
                                            ).show()



                                            callChangePasswordFragment()
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
                                    forgot_password_input_fragment_Progressdialog.visibility=View.INVISIBLE
                                },
                                Response.ErrorListener {

                                    Toast.makeText(
                                        contextParam,
                                        "mSome Error occurred!!!",
                                        Toast.LENGTH_SHORT
                                    ).show()


                                    forgot_password_input_fragment_Progressdialog.visibility=View.INVISIBLE

                                }) {
                                override fun getHeaders(): MutableMap<String, String> {
                                    val headers = HashMap<String, String>()

                                    headers["Content-type"] = "application/json"
                                    headers["token"] = getString(R.string.token)

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


