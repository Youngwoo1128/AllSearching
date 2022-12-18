package com.woojoo.allsearching.ui.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.woojoo.allsearching.R
import com.woojoo.allsearching.databinding.ActivityMainBinding
import com.woojoo.allsearching.ui.BindingActivity
import com.woojoo.allsearching.ui.adapter.MainViewPagerAdapter
import com.woojoo.allsearching.ui.fragment.SearchingResultFragment
import com.woojoo.allsearching.ui.fragment.StorageFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
    }


    private fun initView() {
        val fragmentList = listOf<Fragment>(SearchingResultFragment(), StorageFragment())
        binding.vpFragmentContainer.adapter = MainViewPagerAdapter(this, fragmentList)
        binding.vpFragmentContainer.isUserInputEnabled = false

        binding.bottomNavigation.run {
            setOnItemSelectedListener { item ->
                val fragmentValue = when (item.itemId) {
                    R.id.menu_searching -> FRAGMENT_SEARCHING_RESULT_VALUE
                    else ->  FRAGMENT_STORAGE_VALUE
                }
                binding.vpFragmentContainer.setCurrentItem(fragmentValue, false)
                true
            }
            selectedItemId = R.id.menu_searching
        }
    }

    companion object {
        private const val FRAGMENT_SEARCHING_RESULT_VALUE = 0
        private const val FRAGMENT_STORAGE_VALUE = 1
    }
}