package com.woojoo.kbanktest.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.woojoo.kbanktest.R
import com.woojoo.kbanktest.databinding.FragmentSearchingResultBinding
import com.woojoo.kbanktest.ui.BindingFragment
import com.woojoo.kbanktest.ui.KBViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchingResultFragment: BindingFragment<FragmentSearchingResultBinding>(R.layout.fragment_searching_result) {

    private val viewModel : KBViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setObserver()
    }

    private fun setObserver() {
        viewModel.imageResult.observe(viewLifecycleOwner) {
            Toast.makeText(activity, "Observe", Toast.LENGTH_LONG).show()
        }
    }
}