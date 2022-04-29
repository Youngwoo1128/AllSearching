package com.woojoo.kbanktest.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.woojoo.kbanktest.R
import com.woojoo.kbanktest.databinding.FragmentSearchingResultBinding
import com.woojoo.kbanktest.ui.BindingFragment
import com.woojoo.kbanktest.ui.KBViewModel
import com.woojoo.kbanktest.ui.adapter.ImageResultAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchingResultFragment: BindingFragment<FragmentSearchingResultBinding>(R.layout.fragment_searching_result) {

    private val viewModel: KBViewModel by viewModels()
    private lateinit var adapter : ImageResultAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setObserver()
        initView()
    }

    private fun setObserver() {
        viewModel.imageResult.observe(viewLifecycleOwner) { response ->
            adapter.setItemList(response.documents)
        }
    }

    private fun initView() {
        adapter = ImageResultAdapter()
        binding.rvImageResult.adapter = adapter

        binding.btnSearching.setOnClickListener {
            viewModel.getImageSearchingResult(binding.etSearching.text.toString(), 1)
        }
    }
}