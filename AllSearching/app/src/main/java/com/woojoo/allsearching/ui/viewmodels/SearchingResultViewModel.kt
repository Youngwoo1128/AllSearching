package com.woojoo.allsearching.ui.viewmodels


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.woojoo.allsearching.extension.requestAPI
import com.woojoo.allsearching.domain.entites.Documents
import com.woojoo.allsearching.domain.entites.Researching
import com.woojoo.allsearching.domain.usecases.InsertResearchingUseCase
import com.woojoo.allsearching.domain.usecases.SearchResultUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.collections.ArrayList

@HiltViewModel
class SearchingResultViewModel @Inject constructor(
    private val searchResultUseCase: SearchResultUseCase,
    private val insertResearchingUseCase: InsertResearchingUseCase,
): ViewModel() {

    private val _document = MutableLiveData<ArrayList<Documents>>()
    val document : LiveData<ArrayList<Documents>>
        get() = _document

    fun getSearchingResult(query: String, page: Int) {
        viewModelScope.requestAPI {
            _document.value = searchResultUseCase(query, page)
        }
    }

    fun insertSearchingItem(item: Documents) {
        viewModelScope.requestAPI {
            val item = Researching(
                id = null,
                dateTime = item.datetime!!,
                viewType = item.viewType,
                title = item.title!!,
                thumbnail = item.thumbnail!!
            )
            insertResearchingUseCase(item)
        }
    }

    fun getDocuments(): ArrayList<Documents>? = _document.value

}