package com.albino.restaurantapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import com.albino.restaurantapp.R

lateinit var buttonOkay: Button
lateinit var orderSuccessfullyPlaced: RelativeLayout

class OrderPlacedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_placed)
        orderSuccessfullyPlaced=findViewById(R.id.orderSuccessfullyPlaced)
        buttonOkay=findViewById(R.id.buttonOkay)

        buttonOkay.setOnClickListener(View.OnClickListener {

            val intent= Intent(this,Dashboard::class.java)

            startActivity(intent)

            finishAffinity()//finish all the activities
        })
    }

    override fun onBackPressed() {
        //force user to press okay button to take him to dashboard screen
        //user can't go back
    }

}
