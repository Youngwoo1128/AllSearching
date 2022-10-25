package com.woojoo.allsearching.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.woojoo.allsearching.R
import com.woojoo.allsearching.databinding.ActivityMainBinding
import com.woojoo.allsearching.ui.fragment.SearchingResultFragment
import com.woojoo.allsearching.ui.fragment.StorageFragment
import com.woojoo.allsearching.ui.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(){

    private lateinit var binding : ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()

    // Bottom Navigation 에서 item을 클릭 할때 마다 Fragment가 바뀌는건 Fragment가 onCreate 부터 다시 호출함
    // 이게 문제
    // 그래서 검색 하고 다른 Fragment에 갔다가 다시 돌아오면 init 되어 있음
    // 원인은 replace()
    // 해결 -> observing

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.vm = viewModel
        binding.lifecycleOwner = this
        setContentView(binding.root)

        setObserve()
        setOnBottomNavigation()
    }

    private fun setObserve() {
        viewModel.currentFragmentType.observe(this) {
            changeFragment(it)
        }
    }

    private fun changeFragment(fragmentType: FragmentType) {
       var targetFragment = supportFragmentManager.findFragmentByTag(fragmentType.fragmentTag)

        supportFragmentManager.commit {
            if (targetFragment == null) {
                targetFragment = getFragment(fragmentType)
                add(binding.fragmentContainer.id, targetFragment!!, fragmentType.fragmentTag)
            }

            show(targetFragment!!)

            FragmentType.values()
                .filterNot { it == fragmentType }
                .forEach {
                    supportFragmentManager.findFragmentByTag(it.fragmentTag)?.let {
                        hide(it)
                    }
                }
        }
    }

    private fun setOnBottomNavigation() {
        binding.bottomNavigation.run {
            setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.menu_searching -> {
                        changeFragment(FragmentType.SearchingResultFragment)
                        true
                    }
                    R.id.menu_storage -> {
                        changeFragment(FragmentType.StorageFragment)
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

    private fun getFragment(fragment: FragmentType): Fragment {
        return when(fragment.fragmentTag) {
            "SearchingResultFragment_Tag" -> {
                SearchingResultFragment()
            }
            else -> {
                StorageFragment()
            }
        }
    }
}

enum class FragmentType(val fragmentTitle: String, val fragmentTag: String) {
    SearchingResultFragment("SearchingResultFragment", "SearchingResultFragment_Tag"),
    StorageFragment("StorageFragment", "StorageFragment_Tag")
}