package com.woojoo.allsearching.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.woojoo.allsearching.R
import com.woojoo.allsearching.constant.EXTRA_EMPTY_SEARCHING_KEYWORD
import com.woojoo.allsearching.databinding.FragmentSearchingResultBinding
import com.woojoo.allsearching.domain.entites.Documents
import com.woojoo.allsearching.ui.BindingFragment
import com.woojoo.allsearching.ui.viewmodels.SearchingResultViewModel
import com.woojoo.allsearching.ui.adapter.SearchingResultAdapter
import com.woojoo.allsearching.ui.dialog.*
import com.woojoo.allsearching.utils.showKeyboard
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchingResultFragment : BindingFragment<FragmentSearchingResultBinding>(R.layout.fragment_searching_result) {

    private val viewModel by viewModels<SearchingResultViewModel>()
    private lateinit var adapter: SearchingResultAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFragmentResultListener()
        setObserver()
        initView()
    }

    private fun setObserver() {
        viewModel.insertToRoom.observe(viewLifecycleOwner) {
            Toast.makeText(
                requireContext(),
                requireContext().getString(R.string.string_favorite),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun initView() {
        adapter = SearchingResultAdapter(object : SearchingResultAdapter.InsertSearchingData {
            override fun onInsertSearchingData(item: Documents) {
                viewModel.insertSearchingItem(item)
            }
        })

        binding.rvImageResult.adapter = adapter
        binding.rvImageResult.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                LinearLayoutManager.VERTICAL
            )
        )

        binding.btnSearching.setOnClickListener {
            if (binding.etSearching.text.toString().isNullOrEmpty()) {
                showEmptyKeywordDialog()
            } else {
                lifecycleScope.launch {
                    viewModel.getSearchingResult(binding.etSearching.text.toString())
                        .collectLatest {
                            adapter.submitData(it)
                        }
                }
            }
        }
    }

    private fun setFragmentResultListener() {
        setFragmentResultListener(
            dialogFragmentManager = dialogFragmentManager(),
            requestKey = EMPTY_KEYWORD,
            listener = { _, bundle ->
                when (bundle.getParcelable(EXTRA_EMPTY_SEARCHING_KEYWORD) as? EmptySearchingKeywordDialogAction) {
                    EmptySearchingKeywordDialogAction.EmptySearchingKeyword -> {
                        binding.etSearching.requestFocus()
                        showKeyboard(requireActivity())
                    }
                    else -> Unit
                }
            }
        )
    }

    private fun showEmptyKeywordDialog() {
        showEmptySearchingKeywordDialog(
            dialogFragmentManager = dialogFragmentManager(),
            requestTag = EMPTY_KEYWORD,
            message = requireContext().getString(R.string.string_input_keyword),
            isCancelable = false,
            buttonText = requireContext().getString(R.string.string_ok)
        )
    }

    companion object {
        private const val EMPTY_KEYWORD = "EMPTY_KEYWORD"
    }
}