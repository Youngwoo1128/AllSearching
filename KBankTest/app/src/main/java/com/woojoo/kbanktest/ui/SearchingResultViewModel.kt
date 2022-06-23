package com.woojoo.kbanktest.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.woojoo.kbanktest.constant.RESPONSE_IMAGE_TYPE
import com.woojoo.kbanktest.constant.RESPONSE_VIDEO_TYPE
import com.woojoo.kbanktest.extension.requestAPI
import com.woojoo.kbanktest.model.response.Document
import com.woojoo.kbanktest.model.response.ResImage
import com.woojoo.kbanktest.model.response.ResVideo
import com.woojoo.kbanktest.repository.SearchResultRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchingResultViewModel @Inject constructor(
    private val searchingRepository: SearchResultRepository
): ViewModel() {

    private val _imageResult = MutableLiveData<ResImage>()
    val imageResult : LiveData<ResImage>
        get() = _imageResult

    private val _videoResult = MutableLiveData<ResVideo>()
    val videoResult: LiveData<ResVideo>
        get() = _videoResult

    private val _document = MutableLiveData<ArrayList<Document>>()
    val document : LiveData<ArrayList<Document>>
        get() = _document

    fun getSearchingResult(query: String, page: Int) {
        viewModelScope.requestAPI {

            _imageResult.value = searchingRepository.getImageResult(query, page)
            _imageResult.value!!.type = RESPONSE_IMAGE_TYPE

            _videoResult.value = searchingRepository.getVideoResult(query, page)
            _videoResult.value!!.type = RESPONSE_VIDEO_TYPE

            _document.value = (imageResult.value!!.documents + videoResult.value!!.documents) as ArrayList<Document>

        }
    }
}