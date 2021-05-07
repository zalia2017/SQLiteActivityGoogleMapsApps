package com.example.sqliteactivitygooglemapsapps

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_maps.*
import kotlinx.android.synthetic.main.row_item.*
import kotlinx.android.synthetic.main.row_item.view.*

class ListAdapter(val context: Context, val items: ArrayList<myActivityModel>, val fragment_list: Fragment): RecyclerView.Adapter<ListAdapter.MyViewHolder>(){

    class MyViewHolder(view:View): RecyclerView.ViewHolder(view) {
        val llMain = view.llMain
        val tvTime = view.tvTime
        val tvActivity = view.tvActivity
        val tvAddress = view.tvAddress
        val btn_delete = view.btn_delete
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.MyViewHolder {
       return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_item, parent,false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ListAdapter.MyViewHolder, position: Int) {
        val item = items.get(position)

        holder.tvTime.text = item.time
        holder.tvActivity.text = item.activity
        holder.tvAddress.text = item.address

        if(position % 2 == 0){
             holder.llMain.setBackgroundColor(
                 ContextCompat.getColor(
                     context,
                     R.color.wallet_secondary_text_holo_dark
                 )
             )
        }else{
            holder.llMain.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.wallet_holo_blue_light
                )
            )
        }

        holder.btn_delete.setOnClickListener {
                Toast.makeText(context, "Delete", Toast.LENGTH_SHORT).show()
        }

    }

}

