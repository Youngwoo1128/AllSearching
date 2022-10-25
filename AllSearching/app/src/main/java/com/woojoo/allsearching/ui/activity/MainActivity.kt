package com.woojoo.allsearching.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.woojoo.allsearching.R
import com.woojoo.allsearching.databinding.ActivityMainBinding
import com.woojoo.allsearching.ui.fragment.SearchingResultFragment
import com.woojoo.allsearching.ui.fragment.StorageFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(){

    private lateinit var binding : ActivityMainBinding

    // Bottom Navigation 에서 item을 클릭 할때 마다 Fragment가 바뀌는건 Fragment가 onCreate 부터 다시 호출함
    // 이게 문제
    // 그래서 검색 하고 다른 Fragment에 갔다가 다시 돌아오면 init 되어 있음
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setOnBottomNavigation()
    }

    private fun changeFragment(fragment: Fragment) {
       supportFragmentManager.beginTransaction()
           .replace(binding.fragmentContainer.id, fragment)
           .commit()
    }

    private fun setOnBottomNavigation() {
        binding.bottomNavigation.run {
            setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.menu_searching -> {
                        changeFragment(SearchingResultFragment())
                        true
                    }
                    R.id.menu_storage -> {
                        changeFragment(StorageFragment())
                        true
                    }
                    else -> {
                        Unit
                    }
                }
                false
            }
            selectedItemId = R.id.menu_searching
        }
    }


}