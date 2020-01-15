package com.devtides.coroutinesroom.injection

import android.app.Application
import android.content.Context
import com.devtides.coroutinesroom.model.UserRepository
import com.devtides.coroutinesroom.model.room.UserDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: Application) {

    @Provides
    @Singleton
    fun provideContext(): Context = app

    @Provides
    @Singleton
    fun provideUserRepository(): UserRepository =
        UserRepository(UserDatabase.getDatabase(app).userDao())
}