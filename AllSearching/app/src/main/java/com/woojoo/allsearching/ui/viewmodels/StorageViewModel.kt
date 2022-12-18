package com.woojoo.allsearching.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.woojoo.allsearching.utils.SingleLiveEvent
import com.woojoo.allsearching.domain.entites.Researching
import com.woojoo.allsearching.domain.usecases.DeleteResearchingUseCase
import com.woojoo.allsearching.domain.usecases.GetAllResearchingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StorageViewModel @Inject constructor(
    private val getAllResearchingUseCase: GetAllResearchingUseCase,
    private val deleteResearchingUseCase: DeleteResearchingUseCase,
) : ViewModel() {


    val localResearching: LiveData<List<Researching>>
        get() = _localResearching
    private val _localResearching = MutableLiveData<List<Researching>>()

    val deletedItem: LiveData<Int>
        get() = _deletedItem
    private val _deletedItem = SingleLiveEvent<Int>()

    fun getLocalResearchingList() {
        viewModelScope.launch(Dispatchers.IO) {
            _localResearching.postValue(getAllResearchingUseCase())
            Log.d("researching List Size : ", "${_localResearching.value?.size}")
        }
    }

    fun deleteResearchingItem(item: Researching) {
        viewModelScope.launch(Dispatchers.IO) {
            _deletedItem.postValue(deleteResearchingUseCase(item))
            Log.d("Deleted Item :", "${_deletedItem.value}")
        }
    }
}