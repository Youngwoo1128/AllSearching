package com.woojoo.allsearching.ui.dialog

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import com.woojoo.allsearching.R
import com.woojoo.allsearching.databinding.DialogEmptySearchingKeywordBinding

class EmptySearchingKeywordDialog: BaseDialogFragment<DialogEmptySearchingKeywordBinding>(R.layout.dialog_empty_searching_keyword) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val isCancelAble = arguments?.getBoolean(IS_CANCELABLE) ?: false
        isCancelable = isCancelAble
    }

    companion object {
        const val TAG = "EmptyKeyword"
        private const val REQUEST_TAG = "REQUEST_TAG"
        private const val MESSAGE = "MESSAGE"
        private const val IS_CANCELABLE = "IS_CANCLEABLE"
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