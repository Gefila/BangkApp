package com.example.bangkapp

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.bangkapp.databinding.ActivityFoodDetailBinding
import java.text.NumberFormat
import java.util.Locale

class FoodDetail : AppCompatActivity() {
    private lateinit var binding: ActivityFoodDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFoodDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val food = intent.getParcelableExtra<Food>("food")
        if (food != null) {
            val priceFormatted = NumberFormat.getCurrencyInstance(Locale("id", "ID")).format(food.price).replace(",00", "")
            binding.tvFoodDetailName.text = food.name
            binding.tvFoodDetailPrice.text = priceFormatted
            binding.tvFoodDetailDescription.text = food.description
            Glide.with(this).load(food.image).into(binding.ivFoodDetailImage)
        }
    }
}