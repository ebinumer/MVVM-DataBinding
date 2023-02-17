package com.ebinumer.test.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ebinumer.test.data.LstItemDataModel

class HomeViewModel : ViewModel() {

    var mutableItemData = MutableLiveData<LstItemDataModel>()

    val ItemData: MutableLiveData<LstItemDataModel>
        get() = mutableItemData

}