package com.devtides.coroutinesroom.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.devtides.coroutinesroom.LoginApplication
import com.devtides.coroutinesroom.model.LoginState
import com.devtides.coroutinesroom.model.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    val loginComplete = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()

    @Inject
    lateinit var userRepository: UserRepository

    init {
        (application as LoginApplication).appComponent.inject(this)
    }

    fun login(username: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val user = userRepository.getUser(username)
            if (user == null) {
                error.postValue("User not found")
                return@launch
            }
            if (user.passwordHash == password.hashCode()) {
                LoginState.login(user)
                loginComplete.postValue(true)
            } else {
                error.postValue("Password is incorrect")
            }
        }
    }
}