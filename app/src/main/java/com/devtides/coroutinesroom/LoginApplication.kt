package com.devtides.coroutinesroom

import android.app.Application
import com.devtides.coroutinesroom.injection.AppComponent
import com.devtides.coroutinesroom.injection.AppModule
import com.devtides.coroutinesroom.injection.DaggerAppComponent

class LoginApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = initDagger()
    }

    private fun initDagger(): AppComponent =
        DaggerAppComponent.builder().appModule(AppModule(this)).build()
}