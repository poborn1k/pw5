package com.example.pw6.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pw6.adapter.ListAdapter
import com.example.pw6.database.ProductViewModel
import com.example.pw6.databinding.FragmentListFromDBBinding
import org.koin.android.ext.android.inject

class ListFromDBFragment : Fragment() {
    private lateinit var binding: FragmentListFromDBBinding
    private val viewModel: ProductViewModel by viewModels<ProductViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListFromDBBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = ListAdapter()
        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.readAllData.observe(viewLifecycleOwner, Observer { product ->
            adapter.setData(product)
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = ListFromDBFragment()
    }
}