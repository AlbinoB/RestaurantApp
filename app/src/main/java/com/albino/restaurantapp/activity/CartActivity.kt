package com.albino.restaurantapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.albino.restaurantapp.R
import com.albino.restaurantapp.adapter.CartAdapter
import com.albino.restaurantapp.adapter.RestaurantMenuAdapter
import com.albino.restaurantapp.model.CartItems
import com.albino.restaurantapp.model.RestaurantMenu
import com.albino.restaurantapp.utils.ConnectionManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException



lateinit var toolbar:androidx.appcompat.widget.Toolbar


lateinit var textViewOrderingFrom:TextView
lateinit var buttonPlaceOrder:Button

lateinit var recyclerView: RecyclerView
lateinit var layoutManager: RecyclerView.LayoutManager
lateinit var menuAdapter: CartAdapter
lateinit var restaurantId:String
lateinit var restaurantName:String

var totalAmount=0



var cartListItems = arrayListOf<CartItems>()




class CartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)


        buttonPlaceOrder=findViewById(R.id.buttonPlaceOrder)
        textViewOrderingFrom=findViewById(R.id.textViewOrderingFrom)

        toolbar=findViewById(R.id.toolBar)

        restaurantId=intent.getStringExtra("restaurantId")
        restaurantName=intent.getStringExtra("restaurantName")


        //set the restaurant name
        textViewOrderingFrom.text="Ordering from:"+ restaurantName



        val selectedItemsId= intent.getStringArrayListExtra("selectedItemsId")


        buttonPlaceOrder.setOnClickListener(View.OnClickListener {

            val intent= Intent(this,Dashboard::class.java)

            startActivity(intent)

        })

        setToolBar()








        layoutManager = LinearLayoutManager(this)//set the layout manager

        recyclerView = findViewById(R.id.recyclerViewCart)



        if (ConnectionManager().checkConnectivity(this)) {

            try {

                val queue = Volley.newRequestQueue(this)






                val url = "http://13.235.250.119/v2/restaurants/fetch_result/" + restaurantId

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
                                val cartItemJsonObject = data.getJSONObject(i)

                                if(selectedItemsId.contains(cartItemJsonObject.getString("id")))//if the fetched id is present in the selected id save
                                    {

                                            val menuObject = CartItems(
                                            cartItemJsonObject.getString("id"),
                                            cartItemJsonObject.getString("name"),
                                            cartItemJsonObject.getString("cost_for_one"),
                                            cartItemJsonObject.getString("restaurant_id"))

                                        totalAmount= totalAmount+cartItemJsonObject.getString("cost_for_one").toString().toInt()


                                        cartListItems.add(menuObject)

                                    }





                                //progressBar.visibility = View.GONE

                                menuAdapter = CartAdapter(
                                    this,//pass the relativelayout which has the button to enable it later
                                    cartListItems
                                )//set the adapter with the data

                                recyclerView.adapter =
                                    menuAdapter//bind the  recyclerView to the adapter

                                recyclerView.layoutManager =
                                    layoutManager //bind the  recyclerView to the layoutManager


                                //spacing between list items
                                /*recyclerDashboard.addItemDecoration(
                                    DividerItemDecoration(
                                        recyclerDashboard.context,(layoutManager as LinearLayoutManager).orientation
                                    )
                                )*/
                            }

                            //set the total on the button
                            buttonPlaceOrder.text="Place Order(Total:Rs."+ totalAmount+")"



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


    fun setToolBar(){
        setSupportActionBar(toolbar)
        supportActionBar?.title="Tool Bar"
        supportActionBar?.setHomeButtonEnabled(true)//enables the button on the tool bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)//displays the icon on the button
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back_arrow)//change icon to custom
    }


    }

