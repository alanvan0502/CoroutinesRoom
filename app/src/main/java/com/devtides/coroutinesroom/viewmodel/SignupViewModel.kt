package com.devtides.coroutinesroom.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.devtides.coroutinesroom.LoginApplication
import com.devtides.coroutinesroom.model.LoginState
import com.devtides.coroutinesroom.model.UserRepository
import com.devtides.coroutinesroom.model.data.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class SignupViewModel(application: Application) : AndroidViewModel(application) {

    val signupComplete = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()

    @Inject
    lateinit var userRepository: UserRepository

    init {
        (application as LoginApplication).appComponent.inject(this)
    }

    fun signUp(userName: String, password: String, info: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val currentUser = userRepository.getUser(userName)
                if (currentUser != null) {
                    error.postValue("User already exists")
                    return@launch
                }
                val newUser = User(
                    userName = userName,
                    passwordHash = password.hashCode(),
                    otherInfo = info
                )
                userRepository.insert(newUser)
                LoginState.login(newUser)
                signupComplete.postValue(true)
            } catch (e: Throwable) {
                error.postValue("Something went wrong")
            }
        }
    }

}