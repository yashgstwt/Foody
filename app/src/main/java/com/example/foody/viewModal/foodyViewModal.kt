package com.example.foody.viewModal

import android.graphics.LinearGradient
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FoodyViewModal : ViewModel() {

    private var _showBottomNavBar = MutableLiveData( false)
    val showBottomNavBar :LiveData<Boolean> = _showBottomNavBar

    fun updateShowBottomNavBar(value:Boolean){
        _showBottomNavBar.value = value
    }

    private var _showTopAppBar = MutableLiveData(false)
    val showTopAppBar:LiveData<Boolean> = _showTopAppBar

    fun updateShowTopAppBar(value:Boolean){
        _showTopAppBar.value = value
    }

}