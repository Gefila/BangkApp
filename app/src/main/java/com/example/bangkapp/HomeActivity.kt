package com.example.bangkapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HomeActivity : AppCompatActivity() {

    private lateinit var cardFood:CardView
    private lateinit var cardHotel:CardView
    private lateinit var cardRent:CardView

    fun initComponent(){
        cardFood = findViewById(R.id.food)
        cardHotel = findViewById(R.id.hotel)
        cardRent = findViewById(R.id.rent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initComponent()

        cardFood.setOnClickListener {
            val intent = Intent(this,FoodActivity::class.java)
            startActivity(intent)
        }

        cardHotel.setOnClickListener {
            val intent = Intent(this,Hotel::class.java)
            startActivity(intent)
        }

        cardRent.setOnClickListener {
            val intent = Intent(this,Rent::class.java)
            startActivity(intent)
        }
    }

    fun moveToDetailFood (view: View) {
        val intent = Intent(this, FoodDetail::class.java)
        startActivity(intent)
    }
}