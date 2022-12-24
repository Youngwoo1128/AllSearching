package com.woojoo.allsearching.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.woojoo.allsearching.domain.entites.InsertResult
import com.woojoo.allsearching.domain.entites.Documents
import com.woojoo.allsearching.domain.entites.Error
import com.woojoo.allsearching.domain.usecases.*
import com.woojoo.allsearching.utils.LoadStatus
import com.woojoo.allsearching.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchingResultViewModel @Inject constructor(
    private val searchResultUseCase: SearchResultUseCase,
    private val insertResearchingUseCase: InsertResearchingUseCase,
    private val networkExceptionUseCase: NetworkExceptionUseCase,
    private val checkExistUseCase: CheckExistUseCase
) : ViewModel() {

    val insertToRoom: LiveData<Unit>
        get() = _insertToRoom
    private val _insertToRoom = SingleLiveEvent<Unit>()

    val insertResult: LiveData<InsertResult>
        get() = _insertResult
    private val _insertResult = SingleLiveEvent<InsertResult>()

    val networkException: LiveData<Error>
        get() = _networkException
    private val _networkException = MutableLiveData<Error>()

    val pagingData: LiveData<PagingData<Documents>>
        get() = _pagingData
    private val _pagingData = MutableLiveData<PagingData<Documents>>()

    val loadStatus: LiveData<LoadStatus>
        get() = _loadStatus
    private val _loadStatus = MutableLiveData<LoadStatus>()

    val isExistItem: LiveData<Boolean>
        get() = _isExistItem
    private val _isExistItem = SingleLiveEvent<Boolean>()

    private suspend fun getSearchingResult(query: String): Flow<PagingData<Documents>> {
        return searchResultUseCase(query).cachedIn(viewModelScope)
    }

    fun getPagingData(query: String) {
        viewModelScope.launch {
            getSearchingResult(query).collectLatest {
                _pagingData.value = it
            }
        }
    }

    fun insertSearchingItem(item: Documents) {
        viewModelScope.launch(Dispatchers.IO) {
            val isExistItem = checkExistUseCase(item)
            if (isExistItem) {
                _isExistItem.postValue(true)
            } else {
                insertResearchingUseCase(item).collectLatest { result ->
                    _insertResult.postValue(result)
                }

            }
        }
    }

    fun handleNetworkError(throwable: Throwable) {
        viewModelScope.launch(Dispatchers.IO) {
            _networkException.postValue(networkExceptionUseCase(throwable))
        }
    }

    fun setLoadStatusLoading() {
        _loadStatus.value = LoadStatus.isLoading
    }

    fun setLoadStatusFinish() {
        _loadStatus.value = LoadStatus.loadFinish
    }
}