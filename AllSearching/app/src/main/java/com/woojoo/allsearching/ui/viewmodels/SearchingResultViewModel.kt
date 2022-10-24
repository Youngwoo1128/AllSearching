package com.woojoo.allsearching.ui.viewmodels

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.woojoo.allsearching.SingleLiveEvent
import com.woojoo.allsearching.extension.requestAPI
import com.woojoo.allsearching.domain.entites.Documents
import com.woojoo.allsearching.domain.entites.Researching
import com.woojoo.allsearching.domain.usecases.InsertResearchingUseCase
import com.woojoo.allsearching.domain.usecases.SearchResultUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class SearchingResultViewModel @Inject constructor(
    private val searchResultUseCase: SearchResultUseCase,
    private val insertResearchingUseCase: InsertResearchingUseCase,
): ViewModel() {

    private val _insertToRoom = SingleLiveEvent<Unit>()
    val insertToRoom: LiveData<Unit>
        get() = _insertToRoom


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