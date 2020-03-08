package com.albino.restaurantapp.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.albino.restaurantapp.R
import com.albino.restaurantapp.adapter.OrderHistoryAdapter
import com.albino.restaurantapp.model.OrderHistoryRestaurant
import com.albino.restaurantapp.utils.ConnectionManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException





lateinit var layoutManager1: RecyclerView.LayoutManager
lateinit var menuAdapter1: OrderHistoryAdapter
lateinit var recyclerViewAllOrders:RecyclerView


class OrderHistoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_history)

        recyclerViewAllOrders=findViewById(R.id.recyclerViewAllOrders)

        setItemsForEachRestaurant()

    }


    fun setItemsForEachRestaurant(){


        layoutManager1=LinearLayoutManager(this)

        val orderedRestaurantList=ArrayList<OrderHistoryRestaurant>()

        val sharedPreferencess=this.getSharedPreferences(getString(R.string.shared_preferences),
            Context.MODE_PRIVATE)

        val user_id=sharedPreferencess.getString("user_id","000")

        if (ConnectionManager().checkConnectivity(this)) {

            try {

                val queue = Volley.newRequestQueue(this)

                val url = "http://13.235.250.119/v2/orders/fetch_result/" + user_id

                val jsonObjectRequest = object : JsonObjectRequest(
                    Request.Method.GET,
                    url,
                    null,
                    Response.Listener {
                        println("Response12menu is " + it)

                        val responseJsonObjectData = it.getJSONObject("data")

                        val success = responseJsonObjectData.getBoolean("success")

                        if (success) {

                            val data = responseJsonObjectData.getJSONArray("data")

                            for (i in 0 until data.length()) {
                                val restaurantItemJsonObject = data.getJSONObject(i)

                                    val eachRestaurantObject = OrderHistoryRestaurant(
                                        restaurantItemJsonObject.getString("order_id"),
                                        restaurantItemJsonObject.getString("restaurant_name"),
                                        restaurantItemJsonObject.getString("total_cost"),
                                        restaurantItemJsonObject.getString("order_placed_at").substring(0,10))// only date is extracted



                                orderedRestaurantList.add(eachRestaurantObject)


                                menuAdapter1 = OrderHistoryAdapter(
                                    this,
                                    orderedRestaurantList
                                )//set the adapter with the data

                                recyclerViewAllOrders.adapter = menuAdapter1//bind the  recyclerView to the adapter

                                recyclerViewAllOrders.layoutManager =
                                    layoutManager1 //bind the  recyclerView to the layoutManager

                            }
                        }
                    },
                    Response.ErrorListener {
                        println("Error12menu is " + it)

                        Toast.makeText(
                            this,
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
                    this,
                    "Some Unexpected error occured!!!",
                    Toast.LENGTH_SHORT
                ).show()
            }

        } else {
            val alterDialog = androidx.appcompat.app.AlertDialog.Builder(this)

            alterDialog.setTitle("No Internet")
            alterDialog.setMessage("Internet Connection can't be establish!")
            alterDialog.setPositiveButton("Open Settings") { text, listener ->
                val settingsIntent = Intent(Settings.ACTION_WIRELESS_SETTINGS)//open wifi settings
                startActivity(settingsIntent)
                finish()
            }


        }


    }
}
