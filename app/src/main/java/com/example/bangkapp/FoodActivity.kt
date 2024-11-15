package com.example.bangkapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bangkapp.databinding.ActivityFoodBinding
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerFrameLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FoodActivity : AppCompatActivity() {

    private var foods: List<Food> = emptyList()
    private lateinit var binding: ActivityFoodBinding
    private lateinit var foodAdapter: FoodAdapter
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.shimmerLayout.startShimmer()

        val foodSearch = binding.foodSearch
        foodSearch.clearFocus()
        foodSearch.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                foodSearch.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val searchText = newText!!.toLowerCase()

                val searchList = if(searchText.isNotEmpty()){
                    foods.filter { food ->
                        food.name.toLowerCase().contains(searchText)
                    }
                }else{
                    foods
                }
                foodAdapter.updateData(searchList)

                return false
            }

        })

        foodAdapter = FoodAdapter(foods, object : FoodAdapter.OnItemClickListener{
            override fun onItemClick(food: Food) {
                Toast.makeText(this@FoodActivity, "You clicked on ${food.name}", Toast.LENGTH_SHORT).show()
            }
        })

        binding.rvFood.apply {
            layoutManager = LinearLayoutManager(this@FoodActivity)
            adapter = foodAdapter
        }
        handler = Handler(mainLooper)
        getFood()
    }

    fun getFood(){
        RetrofitClient.foodService.getFood().enqueue(object : Callback<List<Food>> {
            override fun onResponse(call: Call<List<Food>>, response: Response<List<Food>>) {
                if(response.isSuccessful){
                    binding.shimmerLayout.stopShimmer()
                    binding.shimmerLayout.visibility = View.GONE
                    foods = response.body() ?: emptyList()
                    if(foods != null){
                        foodAdapter.updateData(foods)
                    }
                }
            }

            override fun onFailure(call: Call<List<Food>>, t: Throwable) {
                Log.d("FoodActivity", "onFailure: ${t.message}")
                Toast.makeText(this@FoodActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                runnable = Runnable {
                    getFood()
                }
                handler.postDelayed(runnable, 3000)
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(runnable)
    }



}