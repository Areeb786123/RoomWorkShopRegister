package com.areeb.data.locale

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.areeb.data.models.WorkShopEntity

@Dao
interface WorkShopDao {

    @Query("SELECT * FROM workshop_entity")
    suspend fun getAllWorkshop(): List<WorkShopEntity>


    @Insert
    suspend fun insertWorkShopList(workshopList: List<WorkShopEntity>)

}