package com.ekenya.rnd.onboarding.di.injectables

import android.content.Context
import androidx.room.Room
import com.ekenya.rnd.baseapp.di.ModuleScope
import com.ekenya.rnd.onboarding.database.UserDao
import com.ekenya.rnd.onboarding.database.UserDatabase
import dagger.Module
import dagger.Provides


@Module
class RoomModule(private val context: Context) {
    // Add Room database-related providers here
    @Provides
    @ModuleScope
    fun provideContext(): Context {
        return context
    }
    @Provides
    @ModuleScope
    fun provideAppDatabase(context: Context): UserDatabase {
        return UserDatabase.getInstance(context)
    }

    @Provides
    @ModuleScope
    fun provideUserDao(database: UserDatabase): UserDao {
        return database.userDao()
    }
}