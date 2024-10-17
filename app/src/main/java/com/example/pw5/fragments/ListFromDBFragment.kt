package com.example.pw5.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pw5.R
import com.example.pw5.adapter.ListAdapter
import com.example.pw5.database.ProductViewModel
import com.example.pw5.databinding.FragmentListFromDBBinding
import com.example.pw5.databinding.FragmentMainContentBinding

class ListFromDBFragment : Fragment() {
    private lateinit var binding: FragmentListFromDBBinding
    private lateinit var viewModel: ProductViewModel

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

        viewModel = ViewModelProvider(this).get(ProductViewModel::class.java)
        viewModel.readAllData.observe(viewLifecycleOwner, Observer { product ->
            adapter.setData(product)
        })


    }

    companion object {
        @JvmStatic
        fun newInstance() = ListFromDBFragment()
    }
}