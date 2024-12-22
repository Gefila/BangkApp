package com.example.bangkapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class InformationRepliesAdapter(var informationRepliesList: List<InformationReplies>):RecyclerView.Adapter<InformationRepliesAdapter.InformationRepliesViewHolder>() {
    class InformationRepliesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val username = itemView.findViewById<TextView>(R.id.informationRepliesUsername)
        val comment = itemView.findViewById<TextView>(R.id.informationRepliesComment)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): InformationRepliesAdapter.InformationRepliesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_information_replies, parent, false)
        return InformationRepliesViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: InformationRepliesAdapter.InformationRepliesViewHolder,
        position: Int
    ) {
        val informationReplies = informationRepliesList[position]
        holder.username.text = informationReplies.username
        holder.comment.text = informationReplies.comment
    }

    override fun getItemCount(): Int {
        return informationRepliesList.size
    }

    fun updateData(informationRepliesList: List<InformationReplies>){
        this.informationRepliesList = informationRepliesList
        notifyDataSetChanged()
    }


}