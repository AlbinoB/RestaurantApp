package com.albino.restaurantapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import com.albino.restaurantapp.R

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Remove title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        setContentView(R.layout.activity_splash_screen)

        //Remove notification bar

        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            /* Create an Intent that will start the Menu-Activity. */
            val mainIntent =
                Intent(this@SplashScreenActivity, LoginRegisterActivity::class.java)
            finish()
            startActivity(mainIntent)
        }, 2000)


    }
}
