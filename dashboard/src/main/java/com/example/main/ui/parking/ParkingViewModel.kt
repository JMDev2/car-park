package com.example.main.ui.parking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ekenya.rnd.common.model.ParkingResponse
import com.ekenya.rnd.common.model.ParkingResponseItem
import com.ekenya.rnd.common.model.SlotsResponse
import com.ekenya.rnd.common.utils.Resource
import com.example.main.repository.ParkingRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class ParkingViewModel @Inject constructor(private val repository: ParkingRepository): ViewModel() {
    // TODO add the viewmodel code here
    private var parkingSlotLiveData = MutableLiveData<Resource<SlotsResponse?>>()

    init {

        getParkingSlots()
    }

    /*
    get all parkings
     */
    fun getParkingSlots() = viewModelScope.launch {
        repository.getTheSlots().collect(){
            parkingSlotLiveData.postValue(it)
        }
    }

    fun observeSlotsLivedata(): LiveData<Resource<SlotsResponse?>> {
        return parkingSlotLiveData
    }


}