package com.albino.restaurantapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.albino.restaurantapp.R
import com.albino.restaurantapp.model.CartItems


class CartAdapter(val context: Context, val cartItems:ArrayList<CartItems>):RecyclerView.Adapter<CartAdapter.ViewHolderCart>() {


    class ViewHolderCart(view: View): RecyclerView.ViewHolder(view){
        val textViewOrderItem: TextView =view.findViewById(R.id.textViewOrderItem)
        val textViewOrderItemPrice: TextView =view.findViewById(R.id.textViewOrderItemPrice)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCart {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.cart_recycler_view_single_row,parent,false)

        return ViewHolderCart(view)
    }

    override fun getItemCount(): Int {
        return cartItems.size
    }

    override fun onBindViewHolder(holder: ViewHolderCart, position: Int) {
        val cartItemObject=cartItems[position]


        holder.textViewOrderItem.text=cartItemObject.itemName
        holder.textViewOrderItemPrice.text=cartItemObject.itemPrice
    }


}