package com.example.pw6.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.pw6.MainActivity
import com.example.pw6.R
import com.example.pw6.database.Product
import com.example.pw6.database.ProductViewModel
import com.example.pw6.databinding.FragmentMainContentBinding
import com.example.pw6.retrofit.ProductAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainContentFragment : Fragment() {
    private lateinit var binding: FragmentMainContentBinding
    private val productAPI: ProductAPI by inject()
    private val viewModel: ProductViewModel by viewModels<ProductViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainContentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
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
}