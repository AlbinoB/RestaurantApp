package com.albino.restaurantapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.albino.restaurantapp.R
import com.albino.restaurantapp.fragment.RestaurantMenuFragment

class RestaurantMenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_menu)
        openDashboard()
    }

    fun openDashboard(){
        val transaction=supportFragmentManager.beginTransaction()

        transaction.replace(
            R.id.frameLayout,
            RestaurantMenuFragment()
        )//replace the old layout with the new frag  layout

        transaction .commit()//apply changes

        supportActionBar?.title="Menu"//change the title when each new fragment is opened


    }

}
