package com.woojoo.allsearching.ui.fragment

import android.os.Bundle
import android.util.Log
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
import com.woojoo.allsearching.domain.entites.ResponseResult
import com.woojoo.allsearching.extension.IntentProvider
import com.woojoo.allsearching.ui.BindingFragment
import com.woojoo.allsearching.ui.viewmodels.SearchingResultViewModel
import com.woojoo.allsearching.ui.adapter.SearchingResultAdapter
import com.woojoo.allsearching.ui.dialog.*
import com.woojoo.allsearching.utils.showKeyboardOnEditText
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

        viewModel.insertResult.observe(viewLifecycleOwner) { result ->
            showResultToast(result)
        }

        viewModel.test.observe(viewLifecycleOwner) { result ->
//            adapter.submitData(result)
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
            if (binding.editTextSearching.text.toString().isNullOrEmpty()) {
                showEmptyKeywordDialog()
            } else {
                lifecycleScope.launch {
                    //Activity에서 Scope를 열어서 하는게 맞나,, 고민해보기
                    viewModel.getSearchingResult(binding.editTextSearching.text.toString())
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
                        requireContext().showKeyboardOnEditText(binding.editTextSearching)
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

    private fun showResultToast(result : ResponseResult) {
        when (result) {
            is ResponseResult.ResultSuccess -> {
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
                Log.d("throwable Message", "${(result as? ResponseResult.ResultFail)?.throwable?.message}")
                viewModel.retryInsertSearchingItem {

                }
            }
        }
    }

    companion object {
        private const val EMPTY_KEYWORD = "EMPTY_KEYWORD"
    }
}