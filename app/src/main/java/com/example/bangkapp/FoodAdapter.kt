package com.example.bangkapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FoodAdapter(val foodList: List<Food>): RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {
    class FoodViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val foodName = itemView.findViewById<TextView>(R.id.foodName)
        val foodDescription = itemView.findViewById<TextView>(R.id.foodDescription)
        val foodPrice = itemView.findViewById<TextView>(R.id.foodPrice)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodAdapter.FoodViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_food, parent, false)
        return FoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodAdapter.FoodViewHolder, position: Int) {
        val food = foodList[position]
        holder.foodName.text = food.name
        holder.foodDescription.text = food.description
        holder.foodPrice.text = holder.itemView.context.getString(R.string.price, food.price.toString())
    }

    override fun getItemCount(): Int {
        return foodList.size
    }
}