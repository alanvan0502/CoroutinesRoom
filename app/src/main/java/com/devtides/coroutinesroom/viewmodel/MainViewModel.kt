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
import java.lang.Exception
import javax.inject.Inject


class MainViewModel(application: Application) : AndroidViewModel(application) {

    val userDeleted = MutableLiveData<Boolean>()
    val signedOut = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()

    @Inject
    lateinit var userRepository: UserRepository

    init {
        (application as LoginApplication).appComponent.inject(this)
    }

    fun signOut() {
        LoginState.logout()
        signedOut.value = true
    }

    fun deleteUser() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                LoginState.user?.let {
                    userRepository.deleteUser(it.id)
                    LoginState.logout()
                    userDeleted.postValue(true)
                }
            } catch (e: Throwable) {
                error.postValue("Something went wrong")
            }
        }
    }

}