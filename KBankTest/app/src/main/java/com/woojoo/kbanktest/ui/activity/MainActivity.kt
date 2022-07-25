package com.woojoo.kbanktest.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.woojoo.kbanktest.R
import com.woojoo.kbanktest.databinding.ActivityMainBinding
import com.woojoo.kbanktest.ui.fragment.SearchingResultFragment
import com.woojoo.kbanktest.ui.fragment.StorageFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(){

    private lateinit var binding : ActivityMainBinding

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
                    }

                    R.id.menu_storage -> {
                        changeFragment(StorageFragment())
                    }

                    else -> {
                        Unit
                    }
                }
                true
            }
            selectedItemId = R.id.menu_searching
        }
    }


}