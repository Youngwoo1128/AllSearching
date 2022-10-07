package com.woojoo.allsearching.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.woojoo.allsearching.R
import com.woojoo.allsearching.databinding.FragmentSearchingResultBinding
import com.woojoo.allsearching.domain.entites.Documents
import com.woojoo.allsearching.ui.BindingFragment
import com.woojoo.allsearching.ui.viewmodels.SearchingResultViewModel
import com.woojoo.allsearching.ui.adapter.SearchingResultAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchingResultFragment: BindingFragment<FragmentSearchingResultBinding>(R.layout.fragment_searching_result) {

    private val viewModel by activityViewModels<SearchingResultViewModel>()
    private lateinit var adapter : SearchingResultAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setObserver()
        initView()
    }

    private fun setObserver() {
        viewModel.document.observe(viewLifecycleOwner) { response ->
            adapter.addNewItem(response)
            adapter.notifyDataSetChanged()
            Log.d("responseList : ", "${response::class.java}")
        }
    }

    private fun initView() {
        adapter = SearchingResultAdapter(object : SearchingResultAdapter.InsertSearchingData {
            override fun onInsertSearchingData(item: Documents) {
                viewModel.insertSearchingItem(item)
            }
        })
        binding.rvImageResult.adapter = adapter
        binding.rvImageResult.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))

        binding.btnSearching.setOnClickListener {
            adapter.clearSearchingResult()
            viewModel.getSearchingResult(binding.etSearching.text.toString(),1)
        }
    }
}