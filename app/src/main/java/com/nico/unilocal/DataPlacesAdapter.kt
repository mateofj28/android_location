package com.nico.unilocal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DataPlacesAdapter(private val places: List<DataPlace>): RecyclerView.Adapter<DataPlacesAdapter.ViewHolder>(){

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val titleTextView: TextView = itemView.findViewById(R.id.txtTitle)
        val desTextView: TextView = itemView.findViewById(R.id.txtPlacesDes)
        val btnAccept: TextView = itemView.findViewById(R.id.btnAccept)
        val btnReject: TextView = itemView.findViewById(R.id.btnReject)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_dataplaces, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val place = places[position]
        holder.titleTextView.text = place.title
        holder.desTextView.text = place.desc

        holder.btnAccept.setOnClickListener { println("----Accept---- $position" ) }
        holder.btnReject.setOnClickListener { println("----Reject---- $position") }

    }

    override fun getItemCount(): Int {
        return places.size
    }

}