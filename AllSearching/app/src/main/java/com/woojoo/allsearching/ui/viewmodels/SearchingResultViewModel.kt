package com.woojoo.allsearching.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.woojoo.allsearching.extension.requestAPI
import com.woojoo.allsearching.model.response.Document
import com.woojoo.allsearching.model.response.ResImage
import com.woojoo.allsearching.model.response.ResVideo
import com.woojoo.allsearching.repository.Mapper
import com.woojoo.allsearching.repository.SearchResultRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.collections.ArrayList

@HiltViewModel
class SearchingResultViewModel @Inject constructor(
    private val mapper: Mapper
): ViewModel() {

    private val _document = MutableLiveData<ArrayList<Document>>()
    val document : LiveData<ArrayList<Document>>
        get() = _document

    fun getSearchingResult(query: String, page: Int) {
        viewModelScope.requestAPI {
            _document.value = mapper.getTotalList(query, page)
        }
    }
}