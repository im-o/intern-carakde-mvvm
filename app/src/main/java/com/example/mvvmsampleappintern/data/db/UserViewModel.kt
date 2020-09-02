package com.example.mvvmsampleappintern.data.db

import androidx.lifecycle.ViewModel
import com.example.mvvmsampleappintern.data.db.entities.User

/**
 * Created by rivaldy on Sep/01/2020.
 * Find me on my lol Github :D -> https://github.com/im-o
 */

class UserViewModel(private val database: AppDatabase): ViewModel() {
    suspend fun addUser(user: User) = database.getUserDao().updateInsert(user)
}