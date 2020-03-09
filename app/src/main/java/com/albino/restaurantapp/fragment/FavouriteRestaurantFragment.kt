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
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room

import com.albino.restaurantapp.R
import com.albino.restaurantapp.adapter.DashboardFragmentAdapter
import com.albino.restaurantapp.database.RestaurantDatabase
import com.albino.restaurantapp.database.RestaurantEntity
import com.albino.restaurantapp.model.Restaurant
import com.albino.restaurantapp.utils.ConnectionManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException


lateinit var recyclerView:RecyclerView
lateinit var layoutManager: RecyclerView.LayoutManager
lateinit var favouriteAdapter: DashboardFragmentAdapter//using the same adapter here as it has the same functionality
lateinit var favourite_restaurant_fragment_Progressdialog:RelativeLayout

var restaurantInfoList= arrayListOf<Restaurant>()

class FavouriteRestaurantFragment(val contextParam:Context) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favourite_restaurant, container, false)

        layoutManager = LinearLayoutManager(activity)//set the layout manager

        recyclerView = view.findViewById(R.id.recyclerViewFavouriteRestaurant)//recycler view from Dashboard fragment

        favourite_restaurant_fragment_Progressdialog=view.findViewById(R.id.favourite_restaurant_fragment_Progressdialog)

        if (ConnectionManager().checkConnectivity(activity as Context)) {

            favourite_restaurant_fragment_Progressdialog.visibility=View.VISIBLE
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

                            restaurantInfoList.clear()//old listener of jsonObjectRequest are still listening therefore clear is used


                            val data = responseJsonObjectData.getJSONArray("data")

                            for (i in 0 until data.length()) {
                                val restaurantJsonObject = data.getJSONObject(i)

                                val restaurantEntity=RestaurantEntity(
                                    restaurantJsonObject.getString("id"),
                                    restaurantJsonObject.getString("name")
                                )

                                if(DBAsynTask(contextParam,restaurantEntity,1).execute().get())//if restaurant present add
                                {

                                val restaurantObject = Restaurant(
                                    restaurantJsonObject.getString("id"),
                                    restaurantJsonObject.getString("name"),
                                    restaurantJsonObject.getString("rating"),
                                    restaurantJsonObject.getString("cost_for_one"),
                                    restaurantJsonObject.getString("image_url")
                                )

                                    restaurantInfoList.add(restaurantObject)

                                //progressBar.visibility = View.GONE

                                favouriteAdapter = DashboardFragmentAdapter(
                                    activity as Context,
                                    restaurantInfoList
                                )//set the adapter with the data

                                recyclerView.adapter = favouriteAdapter//bind the  recyclerView to the adapter

                                recyclerView.layoutManager = layoutManager //bind the  recyclerView to the layoutManager

                                }
                            }
                            if(restaurantInfoList.size==0){//no items found
                                Toast.makeText(
                                    activity as Context,
                                    "Nothing is added to Favourites",
                                    Toast.LENGTH_SHORT
                                ).show()
                                favourite_restaurant_fragment_Progressdialog.visibility=View.INVISIBLE
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

                        favourite_restaurant_fragment_Progressdialog.visibility=View.INVISIBLE
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
                Toast.makeText(activity as Context,"Some Unexpected error occured!!!", Toast.LENGTH_SHORT).show()
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



    class DBAsynTask(val context: Context, val restaurantEntity: RestaurantEntity, val mode: Int) :
        AsyncTask<Void, Void, Boolean>() {

        val db =
            Room.databaseBuilder(context, RestaurantDatabase::class.java, "restaurant-db").build()

        override fun doInBackground(vararg p0: Void?): Boolean {

            /*
            * Mode 1->check if restaurant is in favourites
            * Mode 2->Save the restaurant into DB as favourites
            * Mode 3-> Remove the favourite restaurant*/


            when (mode) {
                1 -> {
                    val restaurant: RestaurantEntity? = db.restaurantDao()
                        .getRestaurantById(restaurantEntity.restaurantId)
                    db.close()
                    return restaurant != null
                }
                else->return false

            }

        }


    }


}
