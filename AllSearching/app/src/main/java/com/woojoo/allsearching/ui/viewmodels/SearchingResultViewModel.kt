package com.woojoo.allsearching.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.woojoo.allsearching.domain.entites.DataBaseResult
import com.woojoo.allsearching.domain.entites.Documents
import com.woojoo.allsearching.domain.entites.Error
import com.woojoo.allsearching.domain.entites.Researching
import com.woojoo.allsearching.domain.usecases.GetAllResearchingUseCase
import com.woojoo.allsearching.domain.usecases.InsertResearchingUseCase
import com.woojoo.allsearching.domain.usecases.NetworkExceptionUseCase
import com.woojoo.allsearching.domain.usecases.SearchResultUseCase
import com.woojoo.allsearching.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchingResultViewModel @Inject constructor(
    private val searchResultUseCase: SearchResultUseCase,
    private val insertResearchingUseCase: InsertResearchingUseCase,
    private val getAllResearchingUseCase: GetAllResearchingUseCase,
    private val networkExceptionUseCase: NetworkExceptionUseCase
) : BaseViewModel() {

    val insertToRoom: LiveData<Unit>
        get() = _insertToRoom
    private val _insertToRoom = SingleLiveEvent<Unit>()

    val insertResult: LiveData<DataBaseResult>
        get() = _insertResult
    private val _insertResult = SingleLiveEvent<DataBaseResult>()

    val networkException : LiveData<Error>
        get() = _networkException
    private val _networkException = MutableLiveData<Error>()


    suspend fun getSearchingResult(query: String): Flow<PagingData<Documents>> {
        return searchResultUseCase(query).cachedIn(viewModelScope)
    }


    fun insertSearchingItem(item: Documents) {
        viewModelScope.launch(Dispatchers.IO) {
            val savedResearchingList = getAllResearchingUseCase()
            val listIndex = when (savedResearchingList.isEmpty()) {
                true -> 0
                else -> savedResearchingList[savedResearchingList.size - 1].index
            }
            insertResearchingUseCase(
                Researching(
                    id = null,
                    index = listIndex.plus(1),
                    dateTime = item.datetime,
                    viewType = item.viewType,
                    title = item.title,
                    thumbnail = item.thumbnail,
                    url = item.url
                )
            ).onEach { result ->
                _insertResult.value = result
                if (result == DataBaseResult.ResultFail()) {
                    val throwable = result as? DataBaseResult.ResultFail
                    throwable?.throwable?.let {
                        handlingDatabaseError(it)
                    } ?: run {}
                }
            }.launchIn(viewModelScope)
        }
    }

    fun retryInsertSearchingItem(retryCount: Int = 3, retryMethod: () -> Unit) {
        repeat(retryCount) {
            retryMethod.invoke()
        }
    }

    fun handleNetworkError(throwable: Throwable) {
        viewModelScope.launch(Dispatchers.IO) {
            _networkException.postValue(networkExceptionUseCase(throwable))
        }
    }

}