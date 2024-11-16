package com.example.bangkapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.bangkapp.databinding.ActivityHomeBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class HomeActivity : AppCompatActivity() {


    private lateinit var binding: ActivityHomeBinding
    private lateinit var imageSliderAdapter: ImageSliderAdapter
    var imageList = ArrayList<ImageSliderData>()

    private lateinit var handler: Handler
    private lateinit var runnable: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        imageList.add(ImageSliderData("https://static.promediateknologi.id/crop/0x0:0x0/750x500/webp/photo/p1/1005/2024/01/26/sate-madura-1244933025.png"))
        imageList.add(ImageSliderData("https://www.frisianflag.com/storage/app/media/uploaded-files/rendang-padang.jpg"))
        imageList.add(ImageSliderData("https://asset-a.grid.id/crop/0x0:0x0/460x340/photo/2019/08/29/1093597743.jpg"))
        imageList.add(ImageSliderData("https://bankmega.com/media/filer_public/02/48/0248e6c1-b3f3-454f-a4b1-93debf3b1b6b/0-banner-bm-mbm-take-away-nasional2.jpg"))

        handler = Handler(Looper.myLooper()!!)
        runnable = object :Runnable{
            var index = 0
            override fun run() {
                if(index == imageList.size){
                    index = 0
                }
                binding.imageSlider.setCurrentItem(index, true)
                handler.postDelayed(this, 3000)
                index++
            }
        }

        imageSliderAdapter = ImageSliderAdapter(imageList)
        binding.imageSlider.adapter = imageSliderAdapter


        val dotsIndicator = findViewById<DotsIndicator>(R.id.dots_indicator)
        dotsIndicator.attachTo(binding.imageSlider)

        binding.food.setOnClickListener {
            val intent = Intent(this,FoodActivity::class.java)
            startActivity(intent)
        }

        binding.hotel.setOnClickListener {
            val intent = Intent(this,Hotel::class.java)
            startActivity(intent)
        }

        binding.rent.setOnClickListener {
            val intent = Intent(this,Rent::class.java)
            startActivity(intent)
        }
    }

    fun moveToDetailFood (view: View) {
        val intent = Intent(this, FoodDetail::class.java)
        startActivity(intent)
    }

    override fun onStop() {
        super.onStop()
        handler.removeCallbacks(runnable)
    }

    override fun onStart() {
        super.onStart()
        handler.post(runnable)
    }

}