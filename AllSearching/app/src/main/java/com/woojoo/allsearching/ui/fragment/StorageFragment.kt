package com.woojoo.allsearching.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.woojoo.allsearching.R
import com.woojoo.allsearching.databinding.FragmentStorageBinding
import com.woojoo.allsearching.domain.entites.Researching
import com.woojoo.allsearching.ui.BindingFragment
import com.woojoo.allsearching.ui.adapter.StorageAdapter
import com.woojoo.allsearching.ui.viewmodels.StorageViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StorageFragment: BindingFragment<FragmentStorageBinding>(R.layout.fragment_storage) {

    private val viewModel by viewModels<StorageViewModel>()
    private lateinit var adapter: StorageAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setObserver()
        initView()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("yw lifecycle", "onCreate")
    }

    override fun onResume() {
        super.onResume()

        viewModel.getLocalResearchingList()
    }

    private fun setObserver() {
        viewModel.localResearching.observe(viewLifecycleOwner) { result ->
            adapter.addNewItem(result)
        }

        viewModel.deletedItem.observe(viewLifecycleOwner) {
            // 구조 자체를 바꿔야함
            // 현재 adapter에서는 내부 DB의 값들을 먼저 가져온 상태
            // delete 를 하고 notify를 하여도 adapter의 값들은 이미 참조중이기 때문에 변함이 없음
            // 때문에 delete를 할때 다시 내부 DB의 값들을 가져 올 것 인지
            // 아니면 delete를 하고 long을 리턴 받았을때 arrayList의 long 번째 index를 지우고 notify를 할지 고민해보기
        }
    }

    private fun initView() {
        adapter = StorageAdapter(object : StorageAdapter.DeleteLocalItem {
            override fun deleteLocalItem(item: Researching) {
                viewModel.deleteResearchingItem(item)
            }
        })

        binding.rvResearching.adapter = adapter
    }
}