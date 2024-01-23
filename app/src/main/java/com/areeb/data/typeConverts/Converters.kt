package com.areeb.data.typeConverts

import androidx.room.TypeConverter
import com.areeb.data.models.WorkShopEntity
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson

class Converters {
    @TypeConverter
    fun fromString(value: String?): List<WorkShopEntity>? {
        if (value == null) {
            return null
        }
        val listType = object : TypeToken<List<WorkShopEntity>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<WorkShopEntity>?): String? {
        return Gson().toJson(list)
    }
}