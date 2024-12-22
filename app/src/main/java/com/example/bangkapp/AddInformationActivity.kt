package com.example.bangkapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bangkapp.databinding.ActivityAddInformationBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddInformationActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddInformationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAddInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.addInformationBtn.setOnClickListener {
            val username = binding.inputUsername.text.toString()
            val comment = binding.inputComment.text.toString()

            if (username.isNotEmpty() && comment.isNotEmpty()) {
                val information = Information("email", username, comment)
                addInformation(information)
                val intent = Intent(this@AddInformationActivity, MainActivity::class.java)
                intent.putExtra("targetFragment", "informationFragment")
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
                finish()
            }
        }
    }

    private fun addInformation(information: Information) {
        RetrofitClient.informationService.addInformation(information)
            .enqueue(object : Callback<Information> {
                override fun onResponse(call: Call<Information>, response: Response<Information>) {
                    if (response.isSuccessful) {
                        val addedInformation = response.body()
                        Log.d("AddInformationActivity", "Information added: $addedInformation")
                        if (addedInformation != null) {
                            Toast.makeText(
                                this@AddInformationActivity,
                                "Information added successfully",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }

                override fun onFailure(call: Call<Information>, t: Throwable) {
                    Toast.makeText(
                        this@AddInformationActivity,
                        "Error: ${t.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.e("AddInformationActivity", "Error: ${t.message}")
                }
            })
    }
}