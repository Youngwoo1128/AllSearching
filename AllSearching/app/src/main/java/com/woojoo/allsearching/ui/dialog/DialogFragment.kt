package com.woojoo.allsearching.ui.dialog

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.woojoo.allsearching.R

interface DialogFragmentManager {
    fun getDialogFragmentManager(): FragmentManager
}

//부모의 manager
fun FragmentActivity.dialogFragmentManager() = object : DialogFragmentManager {
    override fun getDialogFragmentManager(): FragmentManager {
        return supportFragmentManager
    }
}

//자식의 manager
fun Fragment.dialogFragmentManager() = object : DialogFragmentManager {
    override fun getDialogFragmentManager(): FragmentManager {
        return childFragmentManager
    }
}

fun FragmentActivity.setFragmentResultListener(
    dialogFragmentManager: DialogFragmentManager,
    requestKey: String,
    listener:((requestKey: String, bundle: Bundle) -> Unit)
) {
    dialogFragmentManager.getDialogFragmentManager().setFragmentResultListener(requestKey, this, listener)
}

fun Fragment.setFragmentResultListener(
    dialogFragmentManager: DialogFragmentManager,
    requestKey: String,
    listener: ((requestKey: String, bundle: Bundle) -> Unit)
)  {
    dialogFragmentManager.getDialogFragmentManager().setFragmentResultListener(requestKey, this, listener)
}

fun DialogFragmentManager.show(
    dialogFragmentManager: DialogFragment,
    tag: String
) {
    try {
        if (getDialogFragmentManager().isDestroyed) return
        val findFragment = getDialogFragmentManager().findFragmentByTag(tag)
        if (findFragment != null && findFragment.isAdded) {
            (findFragment as? DialogFragment)?.dismissAllowingStateLoss()
        }
        if (!getDialogFragmentManager().isStateSaved) {
            dialogFragmentManager.showNow(getDialogFragmentManager(),tag)
        }

    }catch (e: Exception) {
        Log.d("Exception in DialogFragment Show", "${e.message}")
        e.printStackTrace()
    }
}

fun DialogFragmentManager.hide(tag: String) {
    try {
        if (getDialogFragmentManager().isDestroyed) return
        val findFragment = getDialogFragmentManager().findFragmentByTag(tag)
        if (findFragment != null && findFragment.isAdded) {
            (findFragment as? DialogFragment)?.let {
                it.dismissAllowingStateLoss()
            }
        }
    }catch (e: Exception) {
        Log.d("Exception in DialogFragment Hide", "${e.message}")
        e.printStackTrace()
    }
}

fun showEmptySearchingKeywordDialog(
    dialogFragmentManager: DialogFragmentManager,
    requestTag: String? = null,
    message: String,
    isCancelable: Boolean,
    buttonText: String
) {
    dialogFragmentManager.show(
        dialogFragmentManager = EmptySearchingKeywordDialog.newInstance(
            requestTag = requestTag,
            message = message,
            isCancelable = isCancelable,
            buttonText = buttonText
        ),
        tag = EmptySearchingKeywordDialog.TAG
    )
}

fun showNetworkExceptionDialog(
    dialogFragmentManager: DialogFragmentManager,
    requestTag: String? = null,
    message: String,
    isCancelable: Boolean,
    buttonText: String
) {
    dialogFragmentManager.show(
        dialogFragmentManager = NetworkExceptionDialog.newInstance(
            requestTag = requestTag,
            message = message,
            isCancelable = isCancelable,
            buttonText = buttonText
        ),
        tag = NetworkExceptionDialog.TAG
    )
}
