package com.example.bangkapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FoodActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_food)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val foods = listOf<Food>(
            Food("Pizza", "Pizza description", 10000),
            Food("Burger", "Burger description", 20000),
            Food("Sushi", "Sushi description", 30000),
            Food("Steak", "Steak description", 40000),
            Food("Salad", "Salad description", 50000),
        )

        val foodAdapter = FoodAdapter(foods)
        findViewById<RecyclerView>(R.id.rvFood).apply {
            layoutManager = LinearLayoutManager(this@FoodActivity)
            adapter = foodAdapter
        }

    }

    fun moveToDetailFood (view: View) {
        val intent = Intent(this, FoodDetail::class.java)
        startActivity(intent)
    }


}