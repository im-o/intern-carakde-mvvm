package com.example.mvvmsampleappintern.ui.userlist

import androidx.lifecycle.ViewModel
import com.example.mvvmsampleappintern.data.repository.UserRepository

/**
 * Created by rivaldy on Sep/07/2020.
 * Find me on my lol Github :D -> https://github.com/im-o
 */

class UserListViewModel(
    private val repository: UserRepository
): ViewModel() {
    suspend fun getAllUser(pageNumber: String) = repository.getAllUser(pageNumber)
}