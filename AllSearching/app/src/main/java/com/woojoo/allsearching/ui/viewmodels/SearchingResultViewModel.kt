package com.woojoo.allsearching.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.woojoo.allsearching.extension.requestAPI
import com.woojoo.allsearching.model.response.Document
import com.woojoo.allsearching.model.response.ResImage
import com.woojoo.allsearching.model.response.ResVideo
import com.woojoo.allsearching.repository.SearchResultRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject
import kotlin.Comparator
import kotlin.collections.ArrayList

@HiltViewModel
class SearchingResultViewModel @Inject constructor(
    private val searchingRepository: SearchResultRepository
): ViewModel() {

    private val _imageResult = MutableLiveData<ResImage>()
    private val _videoResult = MutableLiveData<ResVideo>()

    private val _document = MutableLiveData<ArrayList<Document>>()
    val document : LiveData<ArrayList<Document>>
        get() = _document

    fun getSearchingResult(query: String, page: Int) {
        viewModelScope.requestAPI {
            _imageResult.value = searchingRepository.getImageResult(query, page)
            _videoResult.value = searchingRepository.getVideoResult(query, page)

            _document.value = sortResponse(
                (_imageResult.value!!.documents + _videoResult.value!!.documents) as ArrayList<Document>
            )
        }
    }

    private fun sortResponse(result: ArrayList<Document>): ArrayList<Document> {
        result.distinct()

        for (i in 0 until result.size) {
            result[i].datetime = result[i].datetime?.substring(0 until 10)
        }

        val sortObject = Comparator<Document> { o1, o2 ->
            o1?.datetime?.compareTo(o2?.datetime.toString())!!
        }

        Collections.sort(result, sortObject)
        result.reverse()

        Log.d("responseResult : ", "$result")
        return result
    }
}