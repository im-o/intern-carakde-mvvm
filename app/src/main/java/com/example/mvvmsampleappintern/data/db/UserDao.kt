package com.example.mvvmsampleappintern.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mvvmsampleappintern.data.db.entities.CURRENT_USER_ID
import com.example.mvvmsampleappintern.data.db.entities.User

/**
 * Created by rivaldy on Sep/01/2020.
 * Find me on my lol Github :D -> https://github.com/im-o
 */

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateInsert(user: User): Long

    @Query("SELECT * FROM user WHERE uid = $CURRENT_USER_ID")
    fun getUser(): LiveData<User>
}