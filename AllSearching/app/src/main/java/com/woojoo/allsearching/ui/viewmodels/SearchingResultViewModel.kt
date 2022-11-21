package com.woojoo.allsearching.ui.viewmodels

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.woojoo.allsearching.utils.SingleLiveEvent
import com.woojoo.allsearching.domain.entites.Documents
import com.woojoo.allsearching.domain.entites.Researching
import com.woojoo.allsearching.domain.entites.ResponseResult
import com.woojoo.allsearching.domain.usecases.GetAllResearchingUseCase
import com.woojoo.allsearching.domain.usecases.InsertResearchingUseCase
import com.woojoo.allsearching.domain.usecases.SearchResultUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchingResultViewModel @Inject constructor(
    private val searchResultUseCase: SearchResultUseCase,
    private val insertResearchingUseCase: InsertResearchingUseCase,
    private val getAllResearchingUseCase: GetAllResearchingUseCase
): BaseViewModel() {

    private val _insertToRoom = SingleLiveEvent<Unit>()
    val insertToRoom: LiveData<Unit>
        get() = _insertToRoom

    val insertResult: LiveData<ResponseResult>
        get() = _insertResult
    private val _insertResult = SingleLiveEvent<ResponseResult>()


    suspend fun getSearchingResult(query: String): Flow<PagingData<Documents>> {
        return searchResultUseCase(query).cachedIn(viewModelScope)
    }

  fun insertSearchingItem(item: Documents) {
      viewModelScope.launch {
          val savedResearchingList = getAllResearchingUseCase.invoke()
          val listIndex = when (savedResearchingList.isEmpty()) {
              true -> 0
              else -> savedResearchingList[savedResearchingList.size - 1].index
          }
          insertResearchingUseCase.getResult(Researching(
              id = null,
              index = listIndex.plus(1),
              dateTime = item.datetime,
              viewType = item.viewType,
              title = item.title,
              thumbnail = item.thumbnail,
              url = item.url
          )).take(1).collect { result ->
              _insertResult.value = result
          }
      }
  }


//    fun insertSearchingItem(item: Documents) {
//        viewModelScope.requestAPI {
//
//            val savedResearchingList = getAllResearchingUseCase.invoke()
//            val listIndex = when(savedResearchingList.isEmpty()) {
//                true -> 0
//                else -> savedResearchingList[savedResearchingList.size - 1].index
//            }
//
//            insertResearchingUseCase(Researching(
//                id = null,
//                index = listIndex.plus(1),
//                dateTime = item.datetime,
//                viewType = item.viewType,
//                title = item.title,
//                thumbnail = item.thumbnail,
//                url = item.url
//            ))
//            _insertToRoom.call()
//        }
//    }

}