package com.woojoo.allsearching.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.woojoo.allsearching.SingleLiveEvent
import com.woojoo.allsearching.domain.entites.Researching
import com.woojoo.allsearching.domain.usecases.DeleteResearchingUseCase
import com.woojoo.allsearching.domain.usecases.GetAllResearchingUseCase
import com.woojoo.allsearching.extension.requestAPI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class StorageViewModel @Inject constructor(
    private val getAllResearchingUseCase: GetAllResearchingUseCase,
    private val deleteResearchingUseCase: DeleteResearchingUseCase
    ): ViewModel() {

    val localResearching: LiveData<List<Researching>>
        get() = _localResearching
    private val _localResearching = MutableLiveData<List<Researching>>()

//    val deletedItem : LiveData<Researching>
//        get() = _deletedItem
//    private val _deletedItem = SingleLiveEvent<Researching>()

    val deletedItem : LiveData<Int>
        get() = _deletedItem
    private val _deletedItem = SingleLiveEvent<Int>()

    fun getLocalResearchingList() {
        viewModelScope.requestAPI {
            Log.d("researching List Size : ", "${getAllResearchingUseCase.invoke().size}")
            _localResearching.postValue(getAllResearchingUseCase()!!)
        }
    }

    /*
    * Room이 해당 값을 삭제하면 그 뒤의 Primary 키 값들을 자동으로 업데이트 해주는건지 아닌지 찾아보기
    * 안된다면 loop 돌면서 primary 키값 수정해주는 기능 구현하기
    * */

    fun deleteResearchingItem(item: Researching) {
        viewModelScope.requestAPI {
            _deletedItem.value = item.id?.let {
                deleteResearchingUseCase(it.toInt())
            }
            Log.d("Deleted Item :", "${_deletedItem.value}")
        }
    }

    fun updateResearchingList(primaryKey: Int) {
        viewModelScope.requestAPI {
            val researchingList = getAllResearchingUseCase.invoke()

//            for (i in primaryKey until resear)
        }

    }

}