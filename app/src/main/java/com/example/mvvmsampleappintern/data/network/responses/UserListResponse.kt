package com.example.mvvmsampleappintern.data.network.responses

import com.example.mvvmsampleappintern.data.model.Data
import com.google.gson.annotations.SerializedName

/**
 * Created by rivaldy on Sep/07/2020.
 * Find me on my lol Github :D -> https://github.com/im-o
 */

data class UserListResponse(
    @SerializedName("data")
    val user: MutableList<Data>? = null,
    val page: Int? = null,
    val per_page: Int? = null,
    val total: Int? = null,
    val total_pages: Int? = null
)