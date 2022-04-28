package com.woojoo.kbanktest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.woojoo.kbanktest.R
import com.woojoo.kbanktest.databinding.ActivityMainBinding
import com.woojoo.kbanktest.ui.fragment.SearchingResultFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: KBViewModel by viewModels()

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setFragment()
        binding.btnSearching.setOnClickListener {
            searching(binding.etSearching.text.toString())
        }
    }

    private fun setFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragment_container, SearchingResultFragment())
        transaction.commit()
    }

    private fun searching(searchingText: String) {
        viewModel.getImageSearchingResult(searchingText)
    }
}