package com.woojoo.allsearching.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.woojoo.allsearching.R
import com.woojoo.allsearching.constant.EXTRA_EMPTY_SEARCHING_KEYWORD
import com.woojoo.allsearching.databinding.FragmentSearchingResultBinding
import com.woojoo.allsearching.domain.entites.Documents
import com.woojoo.allsearching.domain.entites.InsertResult
import com.woojoo.allsearching.extension.IntentProvider
import com.woojoo.allsearching.ui.BindingFragment
import com.woojoo.allsearching.ui.viewmodels.SearchingResultViewModel
import com.woojoo.allsearching.ui.adapter.SearchingResultAdapter
import com.woojoo.allsearching.ui.dialog.*
import com.woojoo.allsearching.utils.LoadStatus
import com.woojoo.allsearching.utils.getParcelableValue
import com.woojoo.allsearching.utils.showKeyboardOnEditText
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchingResultFragment :
    BindingFragment<FragmentSearchingResultBinding>(R.layout.fragment_searching_result) {

    private val viewModel by viewModels<SearchingResultViewModel>()
    private lateinit var adapter: SearchingResultAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFragmentResultListener()
        setObserver()
        initView()
        addPagingListener()
    }

    private fun setObserver() {
        viewModel.insertToRoom.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), requireContext().getString(R.string.string_favorite), Toast.LENGTH_SHORT).show()
        }

        viewModel.insertResult.observe(viewLifecycleOwner) { result ->
            showResultToast(result)
        }

        viewModel.networkException.observe(viewLifecycleOwner) {
            showNetworkErrorDialog()
        }

        viewModel.pagingData.observe(viewLifecycleOwner) { pagingData ->
            viewLifecycleOwner.lifecycleScope.launch {
                adapter.submitData(pagingData)
            }
            viewModel.setLoadStatusFinish()
        }

        viewModel.isExistItem.observe(viewLifecycleOwner) { isExist ->
            Toast.makeText(requireContext(), getString(R.string.isExistItem), Toast.LENGTH_SHORT).show()
        }

        viewModel.loadStatus.observe(viewLifecycleOwner) { loadStatus ->
            when (loadStatus) {
                is LoadStatus.isLoading -> binding.progressBar.visibility = View.VISIBLE
                else -> binding.progressBar.visibility = View.GONE
            }
        }
    }

    private fun initView() {
        adapter = SearchingResultAdapter(object : SearchingResultAdapter.InsertSearchingData {
            override fun onInsertSearchingData(item: Documents) {
                viewModel.insertSearchingItem(item)
            }

            override fun onClick(item: Documents) {
                startActivity(IntentProvider.getWebViewIntent(requireContext(), item))
            }
        })

        binding.recyclerViewImageResult.adapter = adapter
        binding.recyclerViewImageResult.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                LinearLayoutManager.VERTICAL
            )
        )

        binding.buttonSearching.setOnClickListener {
            if (binding.editTextSearching.text.toString().isEmpty()) {
                showEmptyKeywordDialog()
            } else {
                viewLifecycleOwner.lifecycleScope.launch {
                    viewModel.setLoadStatusLoading()
                    viewModel.getPagingData(binding.editTextSearching.text.toString())
                }
            }
        }
    }

    private fun setFragmentResultListener() {
        setFragmentResultListener(
            dialogFragmentManager = dialogFragmentManager(),
            requestKey = EMPTY_KEYWORD,
            listener = { _, bundle ->
                when (bundle.getParcelableValue<EmptySearchingKeywordDialogAction>(EXTRA_EMPTY_SEARCHING_KEYWORD)) {
                    EmptySearchingKeywordDialogAction.EmptySearchingKeyword -> {
                        requireContext().showKeyboardOnEditText(binding.editTextSearching)
                    }

                    else -> Unit
                }
            }
        )

        setFragmentResultListener(
            dialogFragmentManager = dialogFragmentManager(),
            requestKey = NETWORK_EXCEPTION,
            listener = { _, _ -> }
        )
    }

    private fun showEmptyKeywordDialog() {
        showEmptySearchingKeywordDialog(
            dialogFragmentManager = dialogFragmentManager(),
            requestTag = EMPTY_KEYWORD,
            message = getString(R.string.string_input_keyword),
            isCancelable = false,
            buttonText = getString(R.string.string_ok)
        )
    }

    private fun showNetworkErrorDialog() {
        showNetworkExceptionDialog(
            dialogFragmentManager = dialogFragmentManager(),
            requestTag = NETWORK_EXCEPTION,
            message = getString(R.string.network_exception),
            isCancelable = false,
            buttonText = getString(R.string.string_ok)
        )
    }

    private fun showResultToast(result: InsertResult) {
        when (result) {
            is InsertResult.ResultSuccess -> {
                Toast.makeText(
                    requireContext(),
                    requireContext().getString(R.string.string_favorite),
                    Toast.LENGTH_SHORT
                ).show()
                Log.d("inserted Item: ", "${result.any}")
            }

            else -> {
                Toast.makeText(
                    requireContext(),
                    requireContext().getString(R.string.string_favorite_fail),
                    Toast.LENGTH_SHORT
                ).show()
                Log.d(
                    "throwable Message",
                    "${(result as? InsertResult.ResultFail)?.throwable?.message}"
                )

            }
        }
    }

    private fun addPagingListener() {
        viewLifecycleOwner.lifecycleScope.launch {
            adapter.loadStateFlow.collectLatest {
                if (it.refresh is LoadState.Error) {
                    viewModel.handleNetworkError((it.refresh as LoadState.Error).error)
                }
            }
        }
    }

    companion object {
        private const val EMPTY_KEYWORD = "EMPTY_KEYWORD"
        private const val NETWORK_EXCEPTION = "NETWORK_EXCEPTION"
    }
}