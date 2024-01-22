package com.areeb.data.repository

import android.util.Log
import com.areeb.data.AppDataBase
import com.areeb.data.models.WorkShopEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class WorkShopRepository @Inject constructor(private val appDataBase: AppDataBase) {
    companion object {
        private const val TAG = "workshopRepo"
    }

    fun getAllWorkshops(): kotlinx.coroutines.flow.Flow<List<WorkShopEntity>> {
        return flow {
            try {
                val data = appDataBase.workshopDao().getAllWorkshop()
                emit(data)
            } catch (e: Exception) {
                Log.e(TAG, e.message.toString())
            }

        }.flowOn(Dispatchers.IO)
    }

    suspend fun addWorkShopList(workShopList: List<WorkShopEntity>) {

        appDataBase.workshopDao().insertWorkShopList(workShopList)

    }


}
