package com.woojoo.allsearching.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.woojoo.allsearching.R
import com.woojoo.allsearching.databinding.FragmentSearchingResultBinding
import com.woojoo.allsearching.domain.entites.Documents
import com.woojoo.allsearching.ui.BindingFragment
import com.woojoo.allsearching.ui.viewmodels.SearchingResultViewModel
import com.woojoo.allsearching.ui.adapter.SearchingResultAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchingResultFragment: BindingFragment<FragmentSearchingResultBinding>(R.layout.fragment_searching_result) {

    private val viewModel by viewModels<SearchingResultViewModel>()

    private lateinit var adapter : SearchingResultAdapter
    private var job: Job? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setObserver()
        initView()
    }

    private fun setObserver() {
        viewModel.insertToRoom.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(),
                requireContext().getString(R.string.string_favorite),
                Toast.LENGTH_SHORT).show()
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
            job?.cancel()

            job = lifecycleScope.launch {
                viewModel.getSearchingResult(binding.etSearching.text.toString()).collectLatest {
                    adapter.submitData(it)
                }
            }
        }
    }
}