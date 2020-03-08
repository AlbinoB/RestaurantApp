package com.albino.restaurantapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.albino.restaurantapp.R
import com.albino.restaurantapp.activity.Dashboard
import com.albino.restaurantapp.activity.RestaurantMenuActivity
import com.albino.restaurantapp.model.Restaurant
import com.albino.restaurantapp.model.RestaurantMenu
import com.squareup.picasso.Picasso

class DashboardFragmentAdapter(val context:Context,var itemList:ArrayList<Restaurant>):RecyclerView.Adapter<DashboardFragmentAdapter.ViewHolderDashboard>() {

    class ViewHolderDashboard(view:View):RecyclerView.ViewHolder(view){

        val imageViewRestaurant:ImageView=view.findViewById(R.id.imageViewRestaurant)
        val textViewRestaurantName: TextView =view.findViewById(R.id.textViewRestaurantName)
        val textViewPricePerPerson: TextView =view.findViewById(R.id.textViewPricePerPerson)
        val textViewRating: TextView =view.findViewById(R.id.textViewRating)
        val llContent:LinearLayout=view.findViewById(R.id.llContent)
        val textViewfavourite:TextView=view.findViewById(R.id.textViewfavourite)



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderDashboard {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.dashboard_recycler_view_single_row,parent,false)



        return ViewHolderDashboard(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolderDashboard, position: Int) {
        /*val text = arrayList.get(position)
        holder.itemView.text=text  // save the data to all the view in the dashboard recycler view single row*/

        val restaurant=itemList[position]//gets the item from the itemList sent in the constructor at the position
        //holder.textView.text=text//fill in the recieved data in the holder


        holder.textViewfavourite.setOnClickListener(View.OnClickListener {
            if(holder.textViewfavourite.getTag().equals("unliked")){
                holder.textViewfavourite.setTag("liked")
                holder.textViewfavourite.background=context.resources.getDrawable(R.drawable.ic_fav_fill)
            }else{
                holder.textViewfavourite.setTag("unliked")
                holder.textViewfavourite.background=context.resources.getDrawable(R.drawable.ic_fav_outline)
            }
        })


        holder.llContent.setOnClickListener(View.OnClickListener {

            println(holder.textViewRestaurantName.getTag().toString())


            val intent=Intent(context,RestaurantMenuActivity::class.java)

            intent.putExtra("restaurantId",holder.textViewRestaurantName.getTag().toString())

            intent.putExtra("restaurantName",holder.textViewRestaurantName.text.toString())


            context.startActivity(intent)



        })




        holder.textViewRestaurantName.setTag(restaurant.restaurantId+"")
        holder.textViewRestaurantName.text=restaurant.restaurantName
        holder.textViewPricePerPerson.text=restaurant.cost_for_one+"/Person "
        holder.textViewRating.text=restaurant.restaurantRating
        //holder.imageViewBook.setBackgroundResource(book.bookImage)
        Picasso.get().load(restaurant.restaurantImage).error(R.drawable.ic_default_image_restaurant).into(holder.imageViewRestaurant);//if the image is not displayed properly we display default image in the error part



    }

    fun filterList(filteredList:ArrayList<Restaurant>){
        itemList=filteredList
        notifyDataSetChanged()
    }


}