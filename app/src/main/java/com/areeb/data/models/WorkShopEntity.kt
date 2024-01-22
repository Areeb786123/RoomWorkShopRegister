package com.areeb.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.areeb.utils.CommonString.Companion.WORK_SHOP_ENTITY

@Entity(tableName = WORK_SHOP_ENTITY)
data class WorkShopEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val workshopName: String,
    val duration: String,
    val image: String
)