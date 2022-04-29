package com.woojoo.kbanktest.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.woojoo.kbanktest.extension.request
import com.woojoo.kbanktest.model.network.response.ResImage
import com.woojoo.kbanktest.repository.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class KBViewModel @Inject constructor(
    private val imageRepository: ImageRepository
): ViewModel() {

    private val _imageResult = MutableLiveData<ResImage>()
    val imageResult: LiveData<ResImage>
        get() = _imageResult

    fun getImageSearchingResult(query: String, page: Int) {
        viewModelScope.request {
            _imageResult.value?.documents?.clear()
            _imageResult.value = imageRepository.getImageResult(query, page)
            Log.d("response : ", "${_imageResult.value}")
        }
    }

}