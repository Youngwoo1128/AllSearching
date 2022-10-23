package com.woojoo.allsearching.ui.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.woojoo.allsearching.R
import com.woojoo.allsearching.constant.EXTRA_EMPTY_SEARCHING_KEYWORD
import com.woojoo.allsearching.databinding.DialogEmptySearchingKeywordBinding
import kotlinx.android.parcel.Parcelize

class EmptySearchingKeywordDialog: BaseDialogFragment<DialogEmptySearchingKeywordBinding>(R.layout.dialog_empty_searching_keyword) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val isCancelAble = arguments?.getBoolean(IS_CANCELABLE) ?: false
        isCancelable = isCancelAble

        initView()
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    private fun initView() {

        binding.tvDialogMainText.text = arguments?.getString(MESSAGE)
        binding.btnCancel.text = arguments?.getString(BUTTON_TEXT)

        binding.btnCancel.setOnClickListener {
            setCloseAction()
        }
    }

    private fun setCloseAction() {
        arguments?.let {
            val tag = it.getString(REQUEST_TAG) ?: return@let

            setFragmentResult(
                requestKey = tag,
                result = bundleOf(
                    EXTRA_EMPTY_SEARCHING_KEYWORD to EmptySearchingKeywordDialogAction.EmptySearchingKeyword
                )
            )
        }
        dismiss()
    }

    companion object {
        const val TAG = "EmptyKeyword"
        private const val REQUEST_TAG = "REQUEST_TAG"
        private const val MESSAGE = "MESSAGE"
        private const val IS_CANCELABLE = "IS_CANCELABLE"
        private const val BUTTON_TEXT = "BUTTON_TEXT"

        @JvmStatic
        fun newInstance(
            requestTag: String? = null,
            message: String = "",
            isCancelable: Boolean = false,
            buttonText: String = ""
        ) = EmptySearchingKeywordDialog().apply {
            arguments = bundleOf(
                REQUEST_TAG to requestTag,
                MESSAGE to message,
                IS_CANCELABLE to isCancelable,
                BUTTON_TEXT to buttonText
            )
        }
    }
}

sealed class EmptySearchingKeywordDialogAction: Parcelable {
    @Parcelize object EmptySearchingKeyword: EmptySearchingKeywordDialogAction()
}