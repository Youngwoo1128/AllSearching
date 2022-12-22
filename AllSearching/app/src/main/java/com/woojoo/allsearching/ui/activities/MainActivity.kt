package com.woojoo.allsearching.ui.activities

import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.woojoo.allsearching.R
import com.woojoo.allsearching.databinding.ActivityMainBinding
import com.woojoo.allsearching.ui.BindingActivity
import com.woojoo.allsearching.ui.adapter.MainViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
    }


    private fun initView() {
        binding.viewPager.adapter = MainViewPagerAdapter(this)

        binding.bottomNavigation.selectedItemId = R.id.menu_searching
        binding.bottomNavigation.apply {
            setOnItemSelectedListener { item ->
                val fragmentValue = when (item.itemId) {
                    R.id.menu_searching -> FRAGMENT_SEARCHING_RESULT_VALUE
                    else ->  FRAGMENT_STORAGE_VALUE
                }
                binding.viewPager.setCurrentItem(fragmentValue, true)
                true
            }
        }

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.bottomNavigation.menu.getItem(position).isChecked = true
            }
        })
    }

    companion object {
        private const val FRAGMENT_SEARCHING_RESULT_VALUE = 0
        private const val FRAGMENT_STORAGE_VALUE = 1
    }
}