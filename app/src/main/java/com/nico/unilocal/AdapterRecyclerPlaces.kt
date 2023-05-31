package com.nico.unilocal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterRecyclerPlaces(var places: ArrayList<Place>):
    RecyclerView.Adapter<AdapterRecyclerPlaces.MyViewHolder>() {

        class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
            val txName: TextView = itemView.findViewById(R.id.txtName)
            val txDesc: TextView = itemView.findViewById(R.id.txtDesc)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        TODO("Not yet implemented")
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.activity_recycler_view_places, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        TODO("Not yet implemented")
        val currentItem = places[position]
        holder.txName.text = currentItem.name
        holder.txDesc.text = currentItem.description
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
        return places.size
    }
}