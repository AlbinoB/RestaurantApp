package com.albino.restaurantapp.activity

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.albino.restaurantapp.R
import com.albino.restaurantapp.fragment.LoginFragment
import com.albino.restaurantapp.fragment.RegisterFragment


class LoginRegisterActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_register)




        openLoginFragment()
    }


    fun openLoginFragment() {
        val fragment = LoginFragment()
        val transaction =supportFragmentManager.beginTransaction()

        transaction.replace(
            R.id.frameLayout,
            LoginFragment()
        )//replace the old layout with the new frag  layout


            transaction.commit()//apply changes

        supportActionBar?.title = "DashBoard"//change the title when each new fragment is opened

    }



    override fun onBackPressed() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.frameLayout)//gets the id og=f the current fragment

        when(currentFragment){
            !is LoginFragment -> openLoginFragment()
            else ->super.onBackPressed()
        }
    }

}
