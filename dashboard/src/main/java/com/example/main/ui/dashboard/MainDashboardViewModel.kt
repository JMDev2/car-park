package com.example.main.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ekenya.rnd.common.model.ParkingResponse
import com.ekenya.rnd.common.utils.Resource
import com.example.main.repository.ParkingRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainDashboardViewModel @Inject constructor(private val repository: ParkingRepository) : ViewModel(){

    private var parkingLiveData = MutableLiveData<Resource<ParkingResponse?>>()

    //loading progressbar
    private val loadingStateLiveData = MutableLiveData<Boolean>()
    fun observeLoadingState(): LiveData<Boolean> = loadingStateLiveData


    init {
        getAllTheParkings()
    }


    /*
    get all parkings
     */
    fun getAllTheParkings() = viewModelScope.launch {
        loadingStateLiveData.value = true // Show the progress dialog
        repository.getParkings().collect {
            loadingStateLiveData.value = false // Hide the progress dialog
            parkingLiveData.postValue(it)
        }
    }


    fun observeParkingsLivedata(): LiveData<Resource<ParkingResponse?>>{
        return parkingLiveData
    }
}