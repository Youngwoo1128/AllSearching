package com.woojoo.allsearching.ui.viewmodels


import android.util.Log
import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.woojoo.allsearching.SingleLiveEvent
import com.woojoo.allsearching.extension.requestAPI
import com.woojoo.allsearching.domain.entites.Documents
import com.woojoo.allsearching.domain.entites.Researching
import com.woojoo.allsearching.domain.usecases.InsertResearchingUseCase
import com.woojoo.allsearching.domain.usecases.SearchResultUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.collections.ArrayList

@HiltViewModel
class SearchingResultViewModel @Inject constructor(
    private val searchResultUseCase: SearchResultUseCase,
    private val insertResearchingUseCase: InsertResearchingUseCase,
): ViewModel() {

//    private val _searchingDocuments = MutableLiveData<ArrayList<Documents>>()
//    val searchingDocuments : LiveData<ArrayList<Documents>>
//        get() = _searchingDocuments

    private val _searchingDocuments = MutableLiveData<Flow<PagingData<Documents>>>()
    val searchingDocuments : LiveData<Flow<PagingData<Documents>>>
        get() = _searchingDocuments


    private val _insertToRoom = SingleLiveEvent<Unit>()
    val insertToRoom: LiveData<Unit>
        get() = _insertToRoom



//    fun getSearchingResult(query: String, page: Int) {
//        viewModelScope.requestAPI {
//            withContext(Dispatchers.IO) {
//                _searchingDocuments.postValue(searchResultUseCase(query, page))
//                Log.d("","")
//            }
//
//        }
//    }

//    fun getSearchingResult(query: String) {
//        viewModelScope.requestAPI {
//            _searchingDocuments.value = searchResultUseCase.invoke(query)
//        }
//    }

    suspend fun getSearchingResult(query: String): Flow<PagingData<Documents>> {
        return searchResultUseCase.invoke(query).cachedIn(viewModelScope)
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