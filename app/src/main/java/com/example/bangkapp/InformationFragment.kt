package com.example.bangkapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bangkapp.databinding.FragmentHomeBinding
import com.example.bangkapp.databinding.FragmentInformationBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InformationFragment : Fragment() {
    private var _binding: FragmentInformationBinding? = null
    private val binding get() = _binding!!

    private lateinit var informationAdapter: InformationAdapter
    private var informationList: List<Information> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInformationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        informationAdapter = InformationAdapter(emptyList())
        binding.rvInformation.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = informationAdapter
        }
        getInformation()

        binding.addInformation.setOnClickListener {
            val intent = Intent(requireContext(), AddInformationActivity::class.java)
            startActivity(intent)
        }
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
                    if(isAdded){
                        Toast.makeText(
                            requireContext(),
                            "Error: ${t.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

            })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}