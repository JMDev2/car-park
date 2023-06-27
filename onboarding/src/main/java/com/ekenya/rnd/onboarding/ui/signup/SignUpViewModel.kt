package com.ekenya.rnd.onboarding.ui.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ekenya.rnd.common.model.User
import com.ekenya.rnd.common.repo.UserRegistrationRepository
import com.ekenya.rnd.common.utils.Resource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class SignUpViewModel @Inject constructor(private val repository: UserRegistrationRepository): ViewModel(){
    //TODO: add viewmodel code here
    private val registerUserLiveDAta = MutableLiveData<Resource<User?>>()

    fun registerUser(user: User) = viewModelScope.launch {
        repository.createUser(user).collect { response ->
            registerUserLiveDAta.setValue(response)
        }
    }

    fun observeRegisterUserLiveData(): LiveData<Resource<User?>>{
        return registerUserLiveDAta
    }
}