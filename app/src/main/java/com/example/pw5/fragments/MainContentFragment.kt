package com.example.pw5.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.pw5.R
import com.example.pw5.database.Product
import com.example.pw5.database.ProductViewModel
import com.example.pw5.databinding.FragmentMainContentBinding
import com.example.pw5.retrofit.ProductAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainContentFragment : Fragment() {
    private lateinit var binding: FragmentMainContentBinding
    private lateinit var viewModel: ProductViewModel
    private lateinit var productAPI: ProductAPI

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainContentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(ProductViewModel::class.java)
        initializeRetrofit()

        binding.addToDbButton.setOnClickListener {
            val indexInput: Int? = binding.indexInput.text.toString().toIntOrNull()

            if (indexInput != null) {
                if (indexInput < 1 || indexInput > 29) {
                    Toast.makeText(requireContext(), "Введен неверный индекс", Toast.LENGTH_SHORT).show()
                } else {
                    CoroutineScope(Dispatchers.IO).launch {
                        val product = productAPI.getProduct(indexInput)
                        activity?.runOnUiThread {
                            val productDB = Product(
                                0,
                                product.title,
                                product.category,
                                product.price,
                                product.rating,
                                product.brand,
                                product.returnPolicy,
                                product.images[0]
                            )
                            viewModel.addProduct(productDB)
                            Toast.makeText(requireContext(), "Данные успешно добавлены", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }

        binding.goToSecondFragment.setOnClickListener {
            findNavController().navigate(R.id.action_mainContentFragment_to_listFromDBFragment)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainContentFragment()
    }

    private fun initializeRetrofit() {
        val retrofitUsers = Retrofit.Builder()
            .baseUrl("https://dummyjson.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        productAPI = retrofitUsers.create(ProductAPI::class.java)
    }
}