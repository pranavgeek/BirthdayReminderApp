package com.example.birthdayproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FriendsAdapter(private val frndList: ArrayList<Friend>) :
    RecyclerView.Adapter<FriendsAdapter.MyViewHolder>() {

    var onDeleteClick : ((Friend) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_of_friends, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentFrnd = frndList[position]
        holder.personName.text = currentFrnd.name
        holder.personLabel.text = currentFrnd.label
        holder.personDate.text = currentFrnd.date
        holder.removeBtn.setOnClickListener{
            onDeleteClick?.invoke(currentFrnd)
        }
    }


    override fun getItemCount(): Int {
       return frndList.size
    }
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val personName : TextView = itemView.findViewById(R.id.personName)
        val personLabel : TextView = itemView.findViewById(R.id.personLabel)
        val personDate : TextView = itemView.findViewById(R.id.personDate)
        val removeBtn : Button = itemView.findViewById(R.id.removeBtn)

    }


}