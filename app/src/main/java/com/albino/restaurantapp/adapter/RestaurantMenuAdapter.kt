package com.albino.restaurantapp.adapter

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.albino.restaurantapp.R
import com.albino.restaurantapp.activity.RestaurantMenuActivity
import com.albino.restaurantapp.model.RestaurantMenu

class RestaurantMenuAdapter(val context:Context,val proceedToCartPassed:RelativeLayout,val restaurantMenu:ArrayList<RestaurantMenu>):RecyclerView.Adapter<RestaurantMenuAdapter.ViewHolderRestaurantMenu>() {


    var itemSelectedCount:Int=0
    lateinit var proceedToCart:RelativeLayout


    class ViewHolderRestaurantMenu(view:View):RecyclerView.ViewHolder(view){
        val textViewSerialNumber:TextView=view.findViewById(R.id.textViewSerialNumber)
        val textViewItemName:TextView=view.findViewById(R.id.textViewItemName)
        val textViewItemPrice:TextView=view.findViewById(R.id.textViewItemPrice)
        val buttonAddToCart:Button=view.findViewById(R.id.buttonAddToCart)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderRestaurantMenu {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.restaurant_menu_recycler_view_single_row,parent,false)

        return ViewHolderRestaurantMenu(view)
    }

    override fun getItemCount(): Int {
        restaurantMenu.size
        return restaurantMenu.size
    }

    override fun onBindViewHolder(holder: ViewHolderRestaurantMenu, position: Int) {
        val restaurantMenuItem=restaurantMenu[position]

        proceedToCart=proceedToCartPassed


        holder.buttonAddToCart.setOnClickListener(View.OnClickListener {




            if(holder.buttonAddToCart.getTag().toString().equals("true"))
            {
                itemSelectedCount--//unselected

                holder.buttonAddToCart.setTag("false")

                holder.buttonAddToCart.text="Add"

                holder.buttonAddToCart.setBackgroundColor(Color.rgb(255, 61, 0))


            }
            else
            {
                itemSelectedCount++//selected

                holder.buttonAddToCart.setTag("true")

                holder.buttonAddToCart.text="Remove"

                holder.buttonAddToCart.setBackgroundColor(Color.YELLOW)



            }

            if(itemSelectedCount>0){
                proceedToCart.visibility=View.VISIBLE
            }
            else{
                proceedToCart.visibility=View.INVISIBLE
            }


        })



        holder.textViewItemName.setTag(restaurantMenuItem.id+"")//save the item id in textViewName Tag ,will be used to add to cart
        holder.textViewSerialNumber.text=(position+1).toString()//position starts from 0
        holder.textViewItemName.text=restaurantMenuItem.name
        holder.textViewItemPrice.text="Rs."+restaurantMenuItem.cost_for_one

        holder.buttonAddToCart.setTag("false")


    }

}