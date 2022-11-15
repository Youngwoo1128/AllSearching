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
import com.woojoo.allsearching.domain.usecases.UpdateResearchingUseCase
import com.woojoo.allsearching.extension.requestAPI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class StorageViewModel @Inject constructor(
    private val getAllResearchingUseCase: GetAllResearchingUseCase,
    private val deleteResearchingUseCase: DeleteResearchingUseCase,
    private val updatedResearchingUseCase: UpdateResearchingUseCase
    ): ViewModel() {

    val localResearching: LiveData<List<Researching>>
        get() = _localResearching
    private val _localResearching = MutableLiveData<List<Researching>>()

    val deletedItem : LiveData<Int>
        get() = _deletedItem
    private val _deletedItem = SingleLiveEvent<Int>()

    val updated = MutableLiveData<Int>()


    fun getLocalResearchingList() {
        viewModelScope.requestAPI {
            Log.d("researching List Size : ", "${getAllResearchingUseCase.invoke().size}")
            _localResearching.postValue(getAllResearchingUseCase()!!)
        }
    }

    fun deleteResearchingItem(item: Researching) {
        viewModelScope.requestAPI {
            _deletedItem.value = deleteResearchingUseCase(item)!!
            Log.d("Deleted Item :", "${_deletedItem.value}")
        }
    }

    //delete 이슈 수정 해결 방안 정리

    fun updateResearchingList(deletedKey: Int) {
        viewModelScope.requestAPI {
            val researchingList = getAllResearchingUseCase.invoke()
            var count = 0L
            for (i in researchingList) {
                i.index = count++
            }
        }

    }


}