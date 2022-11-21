package com.woojoo.allsearching.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.woojoo.allsearching.utils.SingleLiveEvent
import com.woojoo.allsearching.domain.entites.Researching
import com.woojoo.allsearching.domain.usecases.DeleteResearchingUseCase
import com.woojoo.allsearching.domain.usecases.GetAllResearchingUseCase
import com.woojoo.allsearching.domain.usecases.UpdateResearchingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StorageViewModel @Inject constructor(
    private val getAllResearchingUseCase: GetAllResearchingUseCase,
    private val deleteResearchingUseCase: DeleteResearchingUseCase,
    private val updatedResearchingUseCase: UpdateResearchingUseCase
) : BaseViewModel() {

    val localResearching: LiveData<List<Researching>>
        get() = _localResearching
    private val _localResearching = MutableLiveData<List<Researching>>()

    val deletedItem: LiveData<Int>
        get() = _deletedItem
    private val _deletedItem = SingleLiveEvent<Int>()

    fun getLocalResearchingList() {
        viewModelScope.requestAPI {
            _localResearching.value = getAllResearchingUseCase()
            Log.d("researching List Size : ", "${_localResearching.value?.size}")
        }
    }

    fun deleteResearchingItem(item: Researching) {
        viewModelScope.requestAPI {
            _deletedItem.value = deleteResearchingUseCase(item)
            Log.d("Deleted Item :", "${_deletedItem.value}")
        }
    }

    fun updateResearchingList(deletedKey: Int) {
        viewModelScope.requestAPI {
            val researchingList = getAllResearchingUseCase()

            for (i in deletedKey..researchingList.size) {
                val savedItem = researchingList[i - 1]
                savedItem.index = i.toLong()
                updatedResearchingUseCase.invoke(savedItem)
            }
        }
    }
}