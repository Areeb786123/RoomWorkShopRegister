package com.areeb.di

import android.content.Context
import androidx.room.Insert
import androidx.room.Room
import androidx.room.RoomDatabase
import com.areeb.data.AppDataBase
import com.areeb.data.locale.StudentDao
import com.areeb.data.locale.WorkShopDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    fun providesWorkShopDao(appDataBase: AppDataBase): WorkShopDao {
        return appDataBase.workshopDao()
    }

    @Provides
    @Singleton
    fun providesStudentDao(appDataBase: AppDataBase): StudentDao {
        return appDataBase.studentDao()
    }

    @Provides
    @Singleton
    fun providesDataBase(@ApplicationContext context: Context): AppDataBase {
        return Room.databaseBuilder(context, AppDataBase::class.java, "appDataBase").build()
    }


}