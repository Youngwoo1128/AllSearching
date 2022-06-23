package com.woojoo.kbanktest.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.woojoo.kbanktest.R
import com.woojoo.kbanktest.databinding.FragmentSearchingResultBinding
import com.woojoo.kbanktest.model.response.Document
import com.woojoo.kbanktest.ui.BindingFragment
import com.woojoo.kbanktest.ui.SearchingResultViewModel
import com.woojoo.kbanktest.ui.adapter.ImageResultAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchingResultFragment: BindingFragment<FragmentSearchingResultBinding>(R.layout.fragment_searching_result) {

    private val viewModel by activityViewModels<SearchingResultViewModel>()
    private lateinit var adapter : ImageResultAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setObserver()
        initView()
    }

    private fun setObserver() {

        viewModel.imageResult.observe(viewLifecycleOwner) { response ->

        }

        viewModel.videoResult.observe(viewLifecycleOwner) { response ->

        }

        viewModel.document.observe(viewLifecycleOwner) { response ->
            adapter.documents = response
            adapter.notifyDataSetChanged()
            Log.d("responseSize : ", "${response.size}}")
        }
    }

    private fun initView() {
        adapter = ImageResultAdapter()
        binding.rvImageResult.adapter = adapter
        binding.rvImageResult.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))

        binding.btnSearching.setOnClickListener {
            viewModel.getSearchingResult(binding.etSearching.text.toString(),1)
        }
    }
}