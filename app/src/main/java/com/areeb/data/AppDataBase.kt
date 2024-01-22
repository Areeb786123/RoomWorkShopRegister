package com.areeb.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.areeb.data.AppDataBase.Companion.MIGRATION_ONE
import com.areeb.data.locale.StudentDao
import com.areeb.data.locale.WorkShopDao
import com.areeb.data.models.UserEntitiy
import com.areeb.data.models.WorkShopEntity

@Database(entities = [WorkShopEntity::class, UserEntitiy::class], version = MIGRATION_ONE)
abstract class AppDataBase : RoomDatabase() {

    companion object {
        const val MIGRATION_ONE = 1
    }

    abstract fun workshopDao(): WorkShopDao
    abstract fun studentDao(): StudentDao

}