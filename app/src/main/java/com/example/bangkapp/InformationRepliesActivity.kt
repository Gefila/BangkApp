package com.example.bangkapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bangkapp.databinding.ActivityInformationDetailBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InformationRepliesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInformationDetailBinding
    private lateinit var informationRepliesAdapter: InformationRepliesAdapter
    private var informationRepliesList: List<InformationReplies> = emptyList()

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
            Log.d("InformationReplies", "Username: ${information}")
        }
        informationRepliesAdapter = InformationRepliesAdapter(emptyList())
        binding.rvFood.apply {
            layoutManager = LinearLayoutManager(this@InformationRepliesActivity)
            adapter = informationRepliesAdapter
        }


        binding.btnAddReplies.setOnClickListener {
            val username = binding.inputUsername.text.toString()
            val comment = binding.inputComment.text.toString()
            if (username.isNotEmpty() && comment.isNotEmpty()) {
                val informationReplies = InformationReplies(information?.id, username, "email", comment)
                addInformationReplies(informationReplies)
                getInformationReplies(information?.id ?: 0)
            }
        }

        getInformationReplies(information?.id ?: 0)


    }

    fun getInformationReplies(id:Int){
        RetrofitClient.informationRepliesService.getInformationReplies(id).enqueue(object : Callback<List<InformationReplies>> {
            override fun onResponse(
                call: Call<List<InformationReplies>>,
                response: Response<List<InformationReplies>>
            ) {
                if (response.isSuccessful) {
                    informationRepliesList = response.body() ?: emptyList()
                    informationRepliesAdapter.updateData(informationRepliesList)
                }
            }

            override fun onFailure(call: Call<List<InformationReplies>>, t: Throwable) {
                Toast.makeText(
                    this@InformationRepliesActivity,
                    "Error: ${t.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }

        })
    }

    fun addInformationReplies(informationReplies: InformationReplies){
        RetrofitClient.informationRepliesService.addInformationReplies(informationReplies).enqueue(object : Callback<InformationReplies> {
            override fun onResponse(
                call: Call<InformationReplies>,
                response: Response<InformationReplies>
            ) {
                if (response.isSuccessful) {
                    val addedInformationReplies = response.body()
                }
            }

            override fun onFailure(call: Call<InformationReplies>, t: Throwable) {
                Toast.makeText(
                    this@InformationRepliesActivity,
                    "Error: ${t.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }


        })
    }
}