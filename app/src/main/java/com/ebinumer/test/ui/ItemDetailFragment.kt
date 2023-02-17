package com.ebinumer.test.ui

import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ebinumer.test.R
import com.ebinumer.test.databinding.ItemDetailPageBinding
import com.ebinumer.test.viewmodel.HomeViewModel

class ItemDetailFragment :Fragment(){
    lateinit var mBinding:ItemDetailPageBinding
    lateinit var mViewModel:HomeViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater,R.layout.item_detail_page,container,false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        mBinding.viewmodel = mViewModel
        initUi()
    }

    private fun initUi() {
        mViewModel.ItemData.observe(viewLifecycleOwner) {
            Log.e("mname", ":${it.name} ",)
        }
    }
}