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
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.albino.restaurantapp.R
import com.albino.restaurantapp.adapter.DashboardFragmentAdapter
import com.albino.restaurantapp.model.Restaurant
import com.albino.restaurantapp.utils.ConnectionManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException

/**
 * A simple [Fragment] subclass.
 */
class DashboardFragment : Fragment() {



    lateinit var recyclerView:RecyclerView
    lateinit var layoutManager:RecyclerView.LayoutManager
    lateinit var dashboardAdapter:DashboardFragmentAdapter


    var restaurantInfoList= arrayListOf<Restaurant>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)

        layoutManager = LinearLayoutManager(activity)//set the layout manager

        recyclerView = view.findViewById(R.id.recyclerViewDashboard)//recycler view from Dashboard fragment


        if (ConnectionManager().checkConnectivity(activity as Context)) {

            try {

                val queue = Volley.newRequestQueue(activity as Context)

                val url = "http://13.235.250.119/v2/restaurants/fetch_result/"

                val jsonObjectRequest = object : JsonObjectRequest(
                    Request.Method.GET,
                    url,
                    null,
                    Response.Listener {
                        println("Response12 is " + it)

                        val responseJsonObjectData = it.getJSONObject("data")

                        val success = responseJsonObjectData.getBoolean("success")

                        if (success) {

                            val data = responseJsonObjectData.getJSONArray("data")

                            for (i in 0 until data.length()) {
                                val bookJsonObject = data.getJSONObject(i)
                                val bookObject = Restaurant(
                                    bookJsonObject.getString("id"),
                                    bookJsonObject.getString("name"),
                                    bookJsonObject.getString("rating"),
                                    bookJsonObject.getString("cost_for_one"),
                                    bookJsonObject.getString("image_url")
                                )
                                restaurantInfoList.add(bookObject)

                                //progressBar.visibility = View.GONE

                                dashboardAdapter = DashboardFragmentAdapter(
                                    activity as Context,
                                    restaurantInfoList
                                )//set the adapter with the data

                                recyclerView.adapter = dashboardAdapter//bind the  recyclerView to the adapter

                                recyclerView.layoutManager = layoutManager //bind the  recyclerView to the layoutManager


                                //spacing between list items
                                /*recyclerDashboard.addItemDecoration(
                                    DividerItemDecoration(
                                        recyclerDashboard.context,(layoutManager as LinearLayoutManager).orientation
                                    )
                                )*/
                            }


                        }
                    },
                    Response.ErrorListener {
                        println("Error12 is " + it)

                        Toast.makeText(
                            activity as Context,
                            "mSome Error occurred!!!",
                            Toast.LENGTH_SHORT
                        ).show()
                    })

                {
                    override fun getHeaders(): MutableMap<String, String> {
                        val headers=HashMap<String,String>()

                        headers["Content-type"]="application/json"
                        headers["token"]="acdc385cfd7264"

                        return headers
                    }
                }

                queue.add(jsonObjectRequest)

            }catch (e: JSONException){
                    Toast.makeText(activity as Context,"Some Unexpected error occured!!!",Toast.LENGTH_SHORT).show()
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

            return view

    }
}
