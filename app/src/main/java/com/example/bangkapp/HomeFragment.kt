package com.example.bangkapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bangkapp.databinding.FragmentHomeBinding
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var imageSliderAdapter: ImageSliderAdapter
    var imageList = ArrayList<ImageSliderData>()

    private lateinit var handler: Handler
    private lateinit var runnable: Runnable

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        if(imageList.isEmpty()){
            imageList.add(ImageSliderData("https://static.promediateknologi.id/crop/0x0:0x0/750x500/webp/photo/p1/1005/2024/01/26/sate-madura-1244933025.png"))
            imageList.add(ImageSliderData("https://www.frisianflag.com/storage/app/media/uploaded-files/rendang-padang.jpg"))
            imageList.add(ImageSliderData("https://asset-a.grid.id/crop/0x0:0x0/460x340/photo/2019/08/29/1093597743.jpg"))
            imageList.add(ImageSliderData("https://bankmega.com/media/filer_public/02/48/0248e6c1-b3f3-454f-a4b1-93debf3b1b6b/0-banner-bm-mbm-take-away-nasional2.jpg"))
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        handler = Handler(Looper.myLooper()!!)
        runnable = object :Runnable{
            var index = 0
            override fun run() {
                if(index >= imageList.size){
                    index = 0
                }
                binding.imageSlider.setCurrentItem(index, true)
                handler.postDelayed(this, 3000)
                index++
            }
        }

        imageSliderAdapter = ImageSliderAdapter(imageList)
        binding.imageSlider.adapter = imageSliderAdapter


        val dotsIndicator = binding.dotsIndicator
        dotsIndicator.attachTo(binding.imageSlider)

        binding.food.setOnClickListener {
            val intent = Intent(requireContext(),FoodActivity::class.java)
            startActivity(intent)
        }

        binding.hotel.setOnClickListener {
            val intent = Intent(requireContext(),Hotel::class.java)
            startActivity(intent)
        }

        binding.rent.setOnClickListener {
            val intent = Intent(requireContext(),Rent::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun moveToDetailFood (view: View) {
        val intent = Intent(requireContext(), FoodDetail::class.java)
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