package com.example.bangkapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FoodActivity : AppCompatActivity() {

    private lateinit var foods: List<Food>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_food)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //dummy data
        var foods = listOf<Food>(
            Food("Pizza", "Pizza description", 10000),
            Food("Burger", "Burger description", 20000),
            Food("Sushi", "Sushi description", 30000),
            Food("Steak", "Steak description", 40000),
            Food("Salad", "Salad description", 50000),
        )

        val foodAdapter = FoodAdapter(foods, object : FoodAdapter.OnItemClickListener{
            override fun onItemClick(food: Food) {
                Toast.makeText(this@FoodActivity, "You clicked on ${food.name}", Toast.LENGTH_SHORT).show()
            }
        })

        findViewById<RecyclerView>(R.id.rvFood).apply {
            layoutManager = LinearLayoutManager(this@FoodActivity)
            adapter = foodAdapter
        }
        getFood()
    }

    fun getFood(){
        RetrofitClient.foodService.getFood().enqueue(object : Callback<List<Food>> {
            override fun onResponse(call: Call<List<Food>>, response: Response<List<Food>>) {
                if(response.isSuccessful){
                    val foods = response.body()
                }
            }

            override fun onFailure(call: Call<List<Food>>, t: Throwable) {
                Log.d("FoodActivity", "onFailure: ${t.message}")
            }

        })
    }





}