package com.woojoo.allsearching.ui.viewmodels

import android.view.MenuItem
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.woojoo.allsearching.R
import com.woojoo.allsearching.ui.activity.FragmentType

class MainViewModel: ViewModel() {

    val currentFragmentType: LiveData<FragmentType>
        get() = _currentFragmentType
    private val _currentFragmentType = MutableLiveData(FragmentType.SearchingResultFragment)

    fun setCurrentFragment(item: MenuItem): Boolean {
        val menuItemId = item.itemId
        val pageType = getPageType(menuItemId)
        changeCurrentFragmentType(pageType)
        return true
    }

    private fun getPageType(menuItemId: Int): FragmentType {
        return when(menuItemId) {
            R.id.menu_searching -> FragmentType.SearchingResultFragment
            else -> FragmentType.StorageFragment
        }
    }

    private fun changeCurrentFragmentType(fragmentType: FragmentType) {
        if (currentFragmentType.value == fragmentType) return
        _currentFragmentType.value = fragmentType
    }

}