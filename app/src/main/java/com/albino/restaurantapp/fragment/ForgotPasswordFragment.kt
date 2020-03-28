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
import com.albino.restaurantapp.activity.Dashboard
import com.albino.restaurantapp.utils.ConnectionManager
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.fragment_register.*
import org.json.JSONException
import org.json.JSONObject

lateinit var editTextOTP:EditText
lateinit var editTextNewPassword:EditText
lateinit var editTextConfirmPasswordForgot:EditText
lateinit var forgot_password_fragment_Progressdialog:RelativeLayout


class ForgotPasswordFragment(val contextParam:Context,val mobile_number:String) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_forgot_password, container, false)

        var buttonSubmit:Button

        editTextOTP=view.findViewById(R.id.editTextOTP)
        editTextNewPassword=view.findViewById(R.id.editTextNewPassword)
        editTextConfirmPasswordForgot=view.findViewById(R.id.editTextConfirmPasswordForgot)
        buttonSubmit=view.findViewById(R.id.buttonSubmit)
        forgot_password_fragment_Progressdialog=view.findViewById(R.id.forgot_password_fragment_Progressdialog)



        buttonSubmit.setOnClickListener(View.OnClickListener {
            if(editTextOTP.text.isBlank()){
                editTextOTP.setError("OTP missing")
            }else{
                if(editTextNewPassword.text.isBlank())
                {
                    editTextNewPassword.setError("Password Missing")
                }else{
                    if(editTextConfirmPasswordForgot.text.isBlank()){
                        editTextConfirmPasswordForgot.setError("Confirm Password Missing")
                    }else{
                        if((editTextNewPassword.text.toString().toInt()==editTextConfirmPasswordForgot.text.toString().toInt()))
                        {
                            if (ConnectionManager().checkConnectivity(activity as Context)) {

                                forgot_password_fragment_Progressdialog.visibility=View.VISIBLE
                                try {

                                    val loginUser = JSONObject()

                                    loginUser.put("mobile_number", mobile_number)
                                    loginUser.put("password", editTextNewPassword.text.toString())
                                    loginUser.put("otp", editTextOTP.text.toString())

                                    val queue = Volley.newRequestQueue(activity as Context)

                                    val url = "http://"+getString(R.string.ip_address)+"/v2/reset_password/fetch_result"

                                    val jsonObjectRequest = object : JsonObjectRequest(
                                        Request.Method.POST,
                                        url,
                                        loginUser,
                                        Response.Listener {

                                            val responseJsonObjectData = it.getJSONObject("data")

                                            val success = responseJsonObjectData.getBoolean("success")

                                            if (success) {

                                                val serverMessage=responseJsonObjectData.getString("successMessage")

                                                Toast.makeText(
                                                    contextParam,
                                                    serverMessage,
                                                    Toast.LENGTH_SHORT
                                                ).show()

                                                passwordChanged()

                                            } else {
                                                val responseMessageServer =
                                                    responseJsonObjectData.getString("errorMessage")
                                                Toast.makeText(
                                                    contextParam,
                                                    responseMessageServer.toString(),
                                                    Toast.LENGTH_SHORT
                                                ).show()

                                            }
                                            forgot_password_fragment_Progressdialog.visibility=View.INVISIBLE
                                        },
                                        Response.ErrorListener {

                                            forgot_password_fragment_Progressdialog.visibility=View.INVISIBLE

                                            Toast.makeText(
                                                contextParam,
                                                "mSome Error occurred!!!",
                                                Toast.LENGTH_SHORT
                                            ).show()

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

                        }else{

                            editTextConfirmPasswordForgot.setError("Passwords don't match")

                        }
                    }
                }
            }

        })




        return view
    }
    fun passwordChanged(){
        val transaction = fragmentManager?.beginTransaction()

        transaction?.replace(
            R.id.frameLayout,
            LoginFragment(contextParam)
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
