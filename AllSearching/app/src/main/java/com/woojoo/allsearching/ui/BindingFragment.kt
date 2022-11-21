package com.woojoo.allsearching.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.woojoo.allsearching.ui.dialog.*
import androidx.fragment.app.viewModels
import com.woojoo.allsearching.ui.dialog.dialogFragmentManager
import com.woojoo.allsearching.ui.viewmodels.BaseViewModel

open class BindingFragment<T: ViewDataBinding>(@LayoutRes private val layoutId: Int) : Fragment() {

    private var _binding: T? = null
    protected val binding: T
        get() = _binding!!

    private val viewModel by viewModels<BaseViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root

        setFragmentResultListener()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObserve()
    }

    private fun setObserve() {
        viewModel.networkException.observe(viewLifecycleOwner) { result ->
            showNetworkErrorDialog()
        }
    }

    private fun setFragmentResultListener() {
        setFragmentResultListener(
            dialogFragmentManager(),
            NETWORK_EXCEPTION
        ) { _, bundle ->

        }
    }

    private fun showNetworkErrorDialog() {

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        private const val NETWORK_EXCEPTION = "NETWORK_EXCEPTION"
    }
}