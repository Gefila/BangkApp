package com.example.bangkapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bangkapp.databinding.ActivityInformationBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InformationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInformationBinding
    private lateinit var informationAdapter: InformationAdapter
    private var informationList: List<Information> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        informationAdapter = InformationAdapter(emptyList())
        binding.rvInformation.apply {
            layoutManager = LinearLayoutManager(this@InformationActivity)
            adapter = informationAdapter
        }
        getInformation()
    }

    fun getInformation() {
        RetrofitClient.informationService.getInformation()
            .enqueue(object : Callback<List<Information>> {
                override fun onResponse(
                    call: Call<List<Information>>,
                    response: Response<List<Information>>
                ) {
                    if (response.isSuccessful) {
                        informationList = response.body() ?: emptyList()
                        informationAdapter.updateData(informationList)
                    }
                }

                override fun onFailure(call: Call<List<Information>>, t: Throwable) {
                    Toast.makeText(
                        this@InformationActivity,
                        "Error: ${t.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            })
    }
}