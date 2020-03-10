package com.albino.restaurantapp.fragment


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.provider.Settings
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.EditText
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
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
import kotlinx.android.synthetic.main.fragment_dashboard.view.*
import kotlinx.android.synthetic.main.sort_radio_button.view.*
import org.json.JSONException
import java.util.*
import kotlin.Comparator
import kotlin.collections.HashMap

/**
 * A simple [Fragment] subclass.
 */
class DashboardFragment(val contextParam: Context) : Fragment() {



    lateinit var recyclerView:RecyclerView
    lateinit var layoutManager:RecyclerView.LayoutManager
    lateinit var dashboardAdapter:DashboardFragmentAdapter
    lateinit var editTextSearch:EditText
    lateinit var radioButtonView:View
    lateinit var dashboard_fragment_Progressdialog:RelativeLayout
    lateinit var dashboard_fragment_cant_find_restaurant:RelativeLayout



    var restaurantInfoList= arrayListOf<Restaurant>()


    var ratingComparator= Comparator<Restaurant> { rest1, rest2 ->

        if(rest1.restaurantRating.compareTo(rest2.restaurantRating,true)==0){
            rest1.restaurantName.compareTo(rest2.restaurantName,true)
        }
        else{
            rest1.restaurantRating.compareTo(rest2.restaurantRating,true)
        }

    }

    var costComparator= Comparator<Restaurant> { rest1, rest2 ->

            rest1.cost_for_one.compareTo(rest2.cost_for_one,true)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        setHasOptionsMenu(true)


        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)

        layoutManager = LinearLayoutManager(activity)//set the layout manager

        recyclerView = view.findViewById(R.id.recyclerViewDashboard)//recycler view from Dashboard fragment

        editTextSearch=view.findViewById(R.id.editTextSearch)

        dashboard_fragment_Progressdialog=view.findViewById(R.id.dashboard_fragment_Progressdialog)

        dashboard_fragment_cant_find_restaurant=view.findViewById(R.id.dashboard_fragment_cant_find_restaurant)

        fun filterFun(strTyped:String){//to filter the recycler view depending on what is typed
            val filteredList= arrayListOf<Restaurant>()

            for (item in restaurantInfoList){
                if(item.restaurantName.toLowerCase().contains(strTyped.toLowerCase())){//to ignore case and if contained add to new list

                    filteredList.add(item)

                }
            }

            if(filteredList.size==0){
                dashboard_fragment_cant_find_restaurant.visibility=View.VISIBLE
            }
            else{
                dashboard_fragment_cant_find_restaurant.visibility=View.INVISIBLE
            }

            dashboardAdapter.filterList(filteredList)

        }

        editTextSearch.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(strTyped: Editable?) {
                filterFun(strTyped.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
        }
        )


            return view

    }


    fun fetchData(){

        if (ConnectionManager().checkConnectivity(activity as Context)) {

            dashboard_fragment_Progressdialog.visibility=View.VISIBLE
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
                                val restaurantJsonObject = data.getJSONObject(i)
                                val restaurantObject = Restaurant(
                                    restaurantJsonObject.getString("id"),
                                    restaurantJsonObject.getString("name"),
                                    restaurantJsonObject.getString("rating"),
                                    restaurantJsonObject.getString("cost_for_one"),
                                    restaurantJsonObject.getString("image_url")
                                )
                                restaurantInfoList.add(restaurantObject)

                                //progressBar.visibility = View.GONE

                                dashboardAdapter = DashboardFragmentAdapter(
                                    activity as Context,
                                    restaurantInfoList
                                )//set the adapter with the data

                                recyclerView.adapter = dashboardAdapter//bind the  recyclerView to the adapter

                                recyclerView.layoutManager = layoutManager //bind the  recyclerView to the layoutManager

                            }

                        }
                        dashboard_fragment_Progressdialog.visibility=View.INVISIBLE
                    },
                    Response.ErrorListener {
                        dashboard_fragment_Progressdialog.visibility=View.INVISIBLE

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

    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater?.inflate(R.menu.menu_dashboard,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id=item.itemId

        when(id){

            R.id.sort->{
                radioButtonView= View.inflate(contextParam,R.layout.sort_radio_button,null)//radiobutton view for sorting display
                    androidx.appcompat.app.AlertDialog.Builder(activity as Context)
                        .setTitle("Sort By?")
                        .setView(radioButtonView)
                        .setPositiveButton("OK") { text, listener ->
                            if (radioButtonView.radio_high_to_low.isChecked) {
                                Collections.sort(restaurantInfoList, costComparator)
                                restaurantInfoList.reverse()
                                dashboardAdapter.notifyDataSetChanged()//updates the adapter
                            }
                            if (radioButtonView.radio_low_to_high.isChecked) {
                                Collections.sort(restaurantInfoList, costComparator)
                                dashboardAdapter.notifyDataSetChanged()//updates the adapter
                            }
                            if (radioButtonView.radio_rating.isChecked) {
                                Collections.sort(restaurantInfoList, ratingComparator)
                                restaurantInfoList.reverse()
                                dashboardAdapter.notifyDataSetChanged()//updates the adapter
                            }
                        }
                        .setNegativeButton("CANCEL") { text, listener ->

                        }
                        .create()
                        .show()
            }
        }

        return super.onOptionsItemSelected(item)
    }


    override fun onResume() {

        if (ConnectionManager().checkConnectivity(activity as Context)) {
            fetchData()//if internet is available fetch data
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
            alterDialog.setCancelable(false)

            alterDialog.create()
            alterDialog.show()

        }

        super.onResume()
    }
}





