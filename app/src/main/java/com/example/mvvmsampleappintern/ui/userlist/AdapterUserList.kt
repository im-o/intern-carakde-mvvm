package com.example.mvvmsampleappintern.ui.userlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmsampleappintern.data.model.UserList
import com.example.mvvmsampleappintern.databinding.ItemrvUserListBinding

/**
 * Created by rivaldy on Sep/07/2020.
 * Find me on my lol Github :D -> https://github.com/im-o
 */

class AdapterUserList(private val userList: MutableList<UserList>, private val listener: (UserList) -> Unit): RecyclerView.Adapter<AdapterUserList.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemrvUserListBinding.inflate(layoutInflater)
        return UserViewHolder(binding)
    }

    override fun getItemCount() = userList.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bindItem(userList[position], listener)
    }

    class UserViewHolder(private val binding: ItemrvUserListBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindItem(user: UserList, listener: (UserList) -> Unit) {
            val idUser = "Id User : ${user.id.toString()}"
            val emailUser = "Email user : ${user.email.toString()}"
            val firstName = "First name : ${user.first_name.toString()}"
            val lastName = "Last Name : ${user.last_name.toString()}"

            binding.apply {
                tvUserId.text = idUser
                tvUserEmail.text = emailUser
                tvFirstName.text = firstName
                tvLastName.text = lastName

                root.setOnClickListener {
                    listener(user)
                }
            }
        }
    }
}