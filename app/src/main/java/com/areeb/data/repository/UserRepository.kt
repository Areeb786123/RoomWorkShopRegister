package com.areeb.data.repository

import android.util.Log
import com.areeb.data.AppDataBase
import com.areeb.data.models.UserEntitiy
import javax.inject.Inject

class UserRepository @Inject constructor(private val appDataBase: AppDataBase) {


    suspend fun insertUser(userEntitiy: UserEntitiy) {
        try {
            appDataBase.studentDao().storeUser(userEntitiy)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun updateUser(userEntitiy: UserEntitiy) {
        try {
            Log.e("checkUpd", userEntitiy.toString())
            appDataBase.studentDao().updateUser(userEntitiy)
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

    suspend fun getUser(): UserEntitiy {
        try {
            return appDataBase.studentDao().getUser()
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }
}