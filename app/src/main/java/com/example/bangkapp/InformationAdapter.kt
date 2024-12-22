package com.example.bangkapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class InformationAdapter(var informationList: List<Information>, val listener: onItemClickListener): RecyclerView.Adapter<InformationAdapter.InformationViewHolder>() {
    class InformationViewHolder(itemView:View): RecyclerView.ViewHolder(itemView) {
        val username = itemView.findViewById<TextView>(R.id.informationUsername)
        val comment = itemView.findViewById<TextView>(R.id.informationComment)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): InformationAdapter.InformationViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_information, parent, false)
        return InformationViewHolder(view)
    }

    override fun onBindViewHolder(holder: InformationAdapter.InformationViewHolder, position: Int) {
        val information = informationList[position]
        holder.username.text = information.username
        holder.comment.text = information.comment
        holder.itemView.setOnClickListener {
            listener.onItemClick(information)
        }
    }

    override fun getItemCount(): Int = informationList.size

    fun updateData(newInformationList: List<Information>) {
        informationList = newInformationList
        notifyDataSetChanged()
    }

    interface onItemClickListener{
        fun onItemClick(information: Information)
    }


}