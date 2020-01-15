package com.devtides.coroutinesroom.injection

import com.devtides.coroutinesroom.viewmodel.LoginViewModel
import com.devtides.coroutinesroom.viewmodel.MainViewModel
import com.devtides.coroutinesroom.viewmodel.SignupViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(target: LoginViewModel)

    fun inject(target: MainViewModel)

    fun inject(target: SignupViewModel)
}