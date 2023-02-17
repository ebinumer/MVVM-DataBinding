package com.ebinumer.test.ui

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.ebinumer.test.R
import com.ebinumer.test.adapter.ItemsAdapter
import com.ebinumer.test.data.LstItemDataModel
import com.ebinumer.test.databinding.HomePageBinding
import com.ebinumer.test.viewmodel.HomeViewModel

class HomeFragment : Fragment() {
    lateinit var mBinding: HomePageBinding
    lateinit var mViewmodel: HomeViewModel
    lateinit var mAdapter: ItemsAdapter
    lateinit var data: MutableList<LstItemDataModel>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.home_page, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewmodel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        initUi()
    }

    private fun initUi() {
        val itemsList = listOf(
            LstItemDataModel(name = "a item1"),
            LstItemDataModel(name = "b item2"),
            LstItemDataModel(name = "c item3"),
            LstItemDataModel(name = "d item4"),
            LstItemDataModel(name = "e item5"),
        )
        data = itemsList.toMutableList()
        mAdapter = ItemsAdapter(data) { data: LstItemDataModel, position: Int ->
            //this the lambda fun ,here we get the onclick
            mViewmodel.mutableItemData.value = data

            findNavController().navigate(R.id.action_home_fragment_to_detail_page)
        }

        mBinding.apply {
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    val searchResultArray = itemsList.filter { it.name.contains(query.toString()) }
                    Log.e(TAG, "onQueryTextSubmit: $searchResultArray")
                    mAdapter.apply {
                        itemList.clear()
                        if (query.toString().trim().isEmpty()) {
                            itemList.addAll(itemsList)
                            notifyItemRangeChanged(0, itemsList.size - 1)
                        } else {

                            itemList.addAll(searchResultArray)
                            notifyItemRangeChanged(0, searchResultArray.size - 1)
                        }

                    }

                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText.toString().isEmpty()) {
                        mAdapter.apply {
                            itemList.clear()
                            itemList.addAll(itemsList)
                            notifyItemRangeChanged(0, itemsList.size - 1)
                        }

                    } else {
                        val searchResultArray =
                            itemsList.filter { it.name.contains(newText.toString()) }
                        mAdapter.apply {
                            itemList.clear()
                            itemList.addAll(searchResultArray)
                            notifyItemRangeChanged(0, searchResultArray.size - 1)
                        }
                    }
                    return false
                }
            })


            itemsRecyclerview.apply {
//        layoutManager=GridLayoutManager(requireContext(),4)
                adapter = mAdapter
            }
        }
    }

}