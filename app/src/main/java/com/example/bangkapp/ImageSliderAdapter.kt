package com.example.bangkapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.bangkapp.databinding.ItemImageSliderBinding

class ImageSliderAdapter(private val imageList: List<ImageSliderData>): RecyclerView.Adapter<ImageSliderAdapter.ImageViewHolder>() {
    class ImageViewHolder(itemView: ItemImageSliderBinding): RecyclerView.ViewHolder(itemView.root) {
        val binding = itemView
        fun bind(data: ImageSliderData) {
            Glide.with(itemView).load(data.image).into(binding.ivImageSlider)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = ItemImageSliderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(view)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(imageList[position])
    }
}