package com.albino.restaurantapp.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
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


        val sharedPreferencess=getSharedPreferences(getString(R.string.shared_preferences),
            Context.MODE_PRIVATE)


        if(sharedPreferencess.getBoolean("user_logged_in",false)){
            val intent= Intent(this,Dashboard::class.java)
            startActivity(intent)
            finish();
        }else{
            openLoginFragment()
        }

    }


    fun openLoginFragment() {

        val transaction =supportFragmentManager.beginTransaction()

        transaction.replace(
            R.id.frameLayout,
            LoginFragment(this)
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id=item.itemId

        when(id){
            android.R.id.home->{

               openLoginFragment()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
