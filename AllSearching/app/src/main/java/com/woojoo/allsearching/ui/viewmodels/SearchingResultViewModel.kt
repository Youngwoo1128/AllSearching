package com.woojoo.allsearching.ui.viewmodels


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.woojoo.allsearching.SingleLiveEvent
import com.woojoo.allsearching.extension.requestAPI
import com.woojoo.allsearching.domain.entites.Documents
import com.woojoo.allsearching.domain.entites.Researching
import com.woojoo.allsearching.domain.usecases.InsertResearchingUseCase
import com.woojoo.allsearching.domain.usecases.SearchResultUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.collections.ArrayList

@HiltViewModel
class SearchingResultViewModel @Inject constructor(
    private val searchResultUseCase: SearchResultUseCase,
    private val insertResearchingUseCase: InsertResearchingUseCase,
): ViewModel() {

    private val _searchingDocuments = MutableLiveData<ArrayList<Documents>>()
    val searchingDocuments : LiveData<ArrayList<Documents>>
        get() = _searchingDocuments

    private val _insertToRoom = SingleLiveEvent<Unit>()
    val insertToRoom: LiveData<Unit>
        get() = _insertToRoom

    fun getSearchingResult(query: String, page: Int) {
        viewModelScope.requestAPI {
            withContext(Dispatchers.IO) {
                _searchingDocuments.postValue(searchResultUseCase(query, page))
            }

        }
    }

    fun insertSearchingItem(item: Documents) {
        viewModelScope.requestAPI {
            insertResearchingUseCase(Researching(
                id = null,
                dateTime = item.datetime!!,
                viewType = item.viewType,
                title = item.title!!,
                thumbnail = item.thumbnail!!
            ))
            _insertToRoom.call()
        }
    }

}