package com.woojoo.allsearching.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.woojoo.allsearching.R
import com.woojoo.allsearching.databinding.ActivityMainBinding
import com.woojoo.allsearching.ui.adapter.MainViewPagerAdapter
import com.woojoo.allsearching.ui.fragment.SearchingResultFragment
import com.woojoo.allsearching.ui.fragment.StorageFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(){

    private lateinit var binding : ActivityMainBinding

    // Bottom Navigation 에서 item을 클릭 할때 마다 Fragment가 바뀌는건 Fragment가 onCreate 부터 다시 호출함
    // 이게 문제
    // 그래서 검색 하고 다른 Fragment에 갔다가 다시 돌아오면 init 되어 있음
    // 원인은 replace()
    // 해결 -> observing
    // 위 방법으로 하면 데이터는 유지 되나 lifeCycle 별로 callback을 받기가 안됨
    // 그리고 찾아보니 BottomNavigation은 replace를 하기 때문에 원래도 이런 이슈가 있었고 많은 개발자 들이 불편을 겪었다고 함
    // 구글도 이 문제를 인지는 하고 있으나 이 이슈를 고치고 있지 않다고 함
    // 그래서 나의 생각은 BottomNavigation 에 setOnItemSelectedListener 가 있으니
    // Viewpager에게 해당 Fragment를 전달하는것으로 하는게 가장 좋을 것 같음

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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