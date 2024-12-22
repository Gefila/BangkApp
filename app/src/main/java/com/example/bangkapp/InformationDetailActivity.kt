package com.example.bangkapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bangkapp.databinding.ActivityInformationDetailBinding

class InformationDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInformationDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityInformationDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val information = intent.getParcelableExtra<Information>("information")
        if (information != null) {
            binding.informationDetailUsername.text = information.username
            binding.informationDetailComment.text = information.comment
        }

    }
}