package com.example.mvvmsampleappintern.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by rivaldy on Sep/01/2020.
 * Find me on my lol Github :D -> https://github.com/im-o
 */

const val CURRENT_USER_ID = 0

@Entity
data class User(
    @PrimaryKey(autoGenerate = false)
    val uid: Int = CURRENT_USER_ID,
    val token: String? = null
)
