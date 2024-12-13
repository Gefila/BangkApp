package com.example.bangkapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.bangkapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.bottomNavigationView.setOnApplyWindowInsetsListener(null)
        val homeFragment = HomeFragment()
        val whislistFragment = WhislistFragment()
        val informationFragment = InformationFragment()

        val targetFragment = intent.getStringExtra("targetFragment") ?: "homeFragment"

        when (targetFragment) {
            "homeFragment" -> {
                setCurrentFragment(homeFragment)
                binding.bottomNavigationView.selectedItemId = R.id.home
            }
            "whislistFragment" -> {
                setCurrentFragment(whislistFragment)
                binding.bottomNavigationView.selectedItemId = R.id.whislist
            }
            "informationFragment" -> {
                setCurrentFragment(informationFragment)
                binding.bottomNavigationView.selectedItemId = R.id.information
            }
        }



        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> setCurrentFragment(homeFragment)
                R.id.whislist -> setCurrentFragment(whislistFragment)
                R.id.information -> setCurrentFragment(informationFragment)
            }
            true
        }
    }

    fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainer, fragment)
            commit()
        }
    }

}