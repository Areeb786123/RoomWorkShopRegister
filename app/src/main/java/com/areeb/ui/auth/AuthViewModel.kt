package com.areeb.ui.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.areeb.data.models.UserEntitiy
import com.areeb.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    private var _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    private var _currentUser = MutableLiveData<UserEntitiy>()
    val currentUser: LiveData<UserEntitiy> = _currentUser

    fun setErrorMessage(error: String) {
        _errorMessage.value = error
    }
    init {
        getUser()
    }

    fun getUser() {
        viewModelScope.launch {
            _currentUser.value = userRepository.getUser()
        }
    }

    fun storeUser(userEntitiy: UserEntitiy) {
        viewModelScope.launch {
            userRepository.insertUser(userEntitiy)
        }
    }

    fun updateUser(userEntitiy: UserEntitiy) {
        viewModelScope.launch {
            userRepository.updateUser(userEntitiy)
        }
    }


}