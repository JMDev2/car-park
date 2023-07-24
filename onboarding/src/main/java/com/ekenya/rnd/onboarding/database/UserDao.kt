package com.ekenya.rnd.onboarding.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.ekenya.rnd.common.model.UserDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert
    suspend fun saveUser(userDetails: UserDetails)

    @Query("SELECT * FROM user")
    fun getAllUsers(): LiveData<List<UserDetails>>

    @Query("SELECT * FROM user WHERE id = :userId")
    fun getUserById(userId: Int): Flow<UserDetails?> // Note that we're using Flow to observe changes

    @Update
    suspend fun updateUser(userDetails: UserDetails)

    @Delete
    suspend fun deleteUser(userDetails: UserDetails)


    //get user by phone number,
    @Query("SELECT * FROM user WHERE phoneNumber = :phoneNumber")
    suspend fun getUserByPhoneNumber(phoneNumber: String): UserDetails?

    //get the users password by phone number
    @Query("SELECT password FROM user WHERE phoneNumber = :phoneNumber")
    suspend fun getPasswordByPhoneNumber(phoneNumber: String): String?
}