package com.example.bangkapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class FoodAdapter(var foodList: List<Food>, val listener: OnItemClickListener): RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {
    class FoodViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val foodName = itemView.findViewById<TextView>(R.id.foodName)
        val foodDescription = itemView.findViewById<TextView>(R.id.foodDescription)
        val foodPrice = itemView.findViewById<TextView>(R.id.foodPrice)
        val foodImage = itemView.findViewById<ImageView>(R.id.foodImage)
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
        Glide.with(holder.itemView.context).load(food.image).into(holder.foodImage)
        holder.itemView.setOnClickListener {
            listener.onItemClick(food)
        }
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    fun updateData(newFoodList: List<Food>) {
        foodList = newFoodList
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(food: Food)
    }
}