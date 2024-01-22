package com.areeb.data.locale

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.areeb.data.models.UserEntitiy
import com.areeb.data.models.WorkShopEntity


@Dao
interface StudentDao {
    @Insert
    suspend fun storeUser(userEntitiy: UserEntitiy)

    @Delete
    suspend fun deleteUser(userEntitiy: UserEntitiy)

    @Update
    suspend fun updateUser(userEntity: UserEntitiy)

    /*
    * I am going  to store only 1 user at a time
    * */
    @Query("SELECT * FROM user_entity")
    suspend fun getUser(): UserEntitiy


    @Insert
    suspend fun workShopAppliedFor(userEntitiy: UserEntitiy)
}