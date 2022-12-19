package com.woojoo.allsearching.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.woojoo.allsearching.R
import com.woojoo.allsearching.databinding.FragmentStorageBinding
import com.woojoo.allsearching.domain.entites.Researching
import com.woojoo.allsearching.ui.BindingFragment
import com.woojoo.allsearching.ui.adapter.StorageAdapter
import com.woojoo.allsearching.ui.viewmodels.StorageViewModel
import com.woojoo.allsearching.utils.LoadStatus
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StorageFragment: BindingFragment<FragmentStorageBinding>(R.layout.fragment_storage) {

    private val viewModel by activityViewModels<StorageViewModel>()
    private lateinit var adapter: StorageAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setObserver()
        initView()
    }

    override fun onResume() {
        super.onResume()

        viewModel.getLocalResearchingList()
    }

    private fun setObserver() {
        viewModel.localResearching.observe(viewLifecycleOwner) { result ->
            adapter.addNewItem(result)
        }

        viewModel.deletedItem.observe(viewLifecycleOwner) {
            adapter.removeItem(it)
            viewModel.setLoadStatusFinish()
        }

        viewModel.loadStatus.observe(viewLifecycleOwner) { loadStatus ->
            when (loadStatus) {
                is LoadStatus.isLoading -> binding.progressBar.visibility = View.VISIBLE
                else -> binding.progressBar.visibility = View.GONE
            }
        }
    }

    private fun initView() {
        adapter = StorageAdapter(object : StorageAdapter.DeleteLocalItem {
            override fun deleteLocalItem(item: Researching) {
                Log.d("deleted Item", "$item")
                viewModel.setLoadStatusLoading()
                viewModel.deleteResearchingItem(item)
            }
        })

        val gridLayoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerViewResearching.layoutManager = gridLayoutManager
        binding.recyclerViewResearching.adapter = adapter
    }
}