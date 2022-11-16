package com.woojoo.allsearching.utils

import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.EditText


fun Context.showKeyboardOnEditText(editText: EditText) {
    editText.postDelayed({
        editText.requestFocus()
        val inputMethodManager = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)
    }, 100)
}
