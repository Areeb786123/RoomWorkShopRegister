package com.areeb.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.areeb.utils.CommonString.Companion.USER_ENTITY

@Entity(tableName = USER_ENTITY)
data class UserEntitiy(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val firstName: String,
    val lastName: String,
    val age: Int,
)
