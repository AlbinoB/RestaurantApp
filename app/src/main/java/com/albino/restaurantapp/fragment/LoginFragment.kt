package com.albino.restaurantapp.fragment


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.app.ActivityCompat

import com.albino.restaurantapp.R
import com.albino.restaurantapp.activity.Dashboard
import com.albino.restaurantapp.activity.LoginRegisterActivity
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
class LoginFragment(val contextParam:Context) : Fragment() {

    lateinit var textViewSignUp: TextView
    lateinit var editTextMobileNumber: EditText
    lateinit var editTextPassword: EditText
    lateinit var textViewForgotPassword: TextView
    lateinit var buttonLogin:Button
    lateinit var login_fragment_Progressdialog:RelativeLayout



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
        login_fragment_Progressdialog=view.findViewById(R.id.login_fragment_Progressdialog)

        login_fragment_Progressdialog.visibility=View.INVISIBLE

        textViewForgotPassword.paintFlags=Paint.UNDERLINE_TEXT_FLAG//under line text

        textViewSignUp.paintFlags=Paint.UNDERLINE_TEXT_FLAG//under line text


        textViewForgotPassword.setOnClickListener(View.OnClickListener {
            openForgotPasswordInputFragment()
        })


        textViewSignUp.setOnClickListener(View.OnClickListener {
            openRegisterFragment()
        })


        buttonLogin.setOnClickListener(View.OnClickListener {

            buttonLogin.visibility=View.INVISIBLE

            loginUserFun()
        })
        return view
    }


    fun openForgotPasswordInputFragment(){
        val transaction = fragmentManager?.beginTransaction()

        transaction?.replace(
            R.id.frameLayout,
            ForgotPasswordInputFragment(contextParam)
        )//replace the old layout with the new frag  layout

        transaction?.commit()//apply changes

    }


    fun openRegisterFragment() {

        val transaction = fragmentManager?.beginTransaction()

        transaction?.replace(
            R.id.frameLayout,
            RegisterFragment(contextParam)
        )//replace the old layout with the new frag  layout

        transaction?.commit()//apply changes


    }


    fun loginUserFun(){


        val sharedPreferencess=contextParam.getSharedPreferences(getString(R.string.shared_preferences),Context.MODE_PRIVATE)


        if (ConnectionManager().checkConnectivity(activity as Context)) {

            //login_fragment_Progressdialog.visibility=View.VISIBLE
                try {

                    val loginUser = JSONObject()

                    loginUser.put("mobile_number", editTextMobileNumber.text)
                    loginUser.put("password", editTextPassword.text)



                    val queue = Volley.newRequestQueue(activity as Context)

                    val url = "http://13.235.250.119/v2/login/fetch_result"

                    val jsonObjectRequest = object : JsonObjectRequest(
                        Request.Method.POST,
                        url,
                        loginUser,
                        Response.Listener {

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
                                    "Welcome "+data.getString("name"),
                                    Toast.LENGTH_SHORT
                                ).show()

                                userSuccessfullyLoggedIn()//after we get a response we call the Log the user in

                            } else {

                                buttonLogin.visibility=View.VISIBLE

                                val responseMessageServer =
                                    responseJsonObjectData.getString("errorMessage")
                                Toast.makeText(
                                    contextParam,
                                    responseMessageServer.toString(),
                                    Toast.LENGTH_SHORT
                                ).show()

                            }
                            login_fragment_Progressdialog.visibility=View.INVISIBLE
                        },
                        Response.ErrorListener {

                            buttonLogin.visibility=View.VISIBLE

                            login_fragment_Progressdialog.visibility=View.INVISIBLE

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
                    buttonLogin.visibility=View.VISIBLE

                    Toast.makeText(
                        contextParam,
                        "Some unexpected error occured!!!",
                        Toast.LENGTH_SHORT
                    ).show()

                }

        }else
        {
            buttonLogin.visibility=View.VISIBLE

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

    fun userSuccessfullyLoggedIn(){
        val intent=Intent(activity as Context,Dashboard::class.java)
        startActivity(intent)
        getActivity()?.finish();
    }



    override fun onResume() {

        if (!ConnectionManager().checkConnectivity(activity as Context))
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
            alterDialog.setCancelable(false)

            alterDialog.create()
            alterDialog.show()

        }

        super.onResume()
    }


}
