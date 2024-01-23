package com.areeb.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.areeb.data.AppDataBase.Companion.MIGRATION_ONE
import com.areeb.data.locale.StudentDao
import com.areeb.data.locale.WorkShopDao
import com.areeb.data.models.UserEntitiy
import com.areeb.data.models.WorkShopEntity
import com.areeb.data.typeConverts.Converters

@Database(entities = [WorkShopEntity::class, UserEntitiy::class], version = MIGRATION_ONE)
@TypeConverters(Converters::class)
abstract class AppDataBase : RoomDatabase() {

    companion object {
        const val MIGRATION_ONE = 1
    }

    abstract fun workshopDao(): WorkShopDao
    abstract fun studentDao(): StudentDao

}