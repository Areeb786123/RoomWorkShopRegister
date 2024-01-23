package com.areeb.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.areeb.data.typeConverts.Converters
import com.areeb.utils.CommonString.Companion.USER_ENTITY

@Entity(tableName = USER_ENTITY)
@TypeConverters(Converters::class)
data class UserEntitiy(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val firstName: String,
    val lastName: String,
    val email: String,
    val age: Int,
    val workShopAppliedFor: List<WorkShopEntity>? = null
)
