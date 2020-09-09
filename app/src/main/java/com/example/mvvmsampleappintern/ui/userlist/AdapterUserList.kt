package com.example.mvvmsampleappintern.ui.userlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmsampleappintern.data.model.Data
import com.example.mvvmsampleappintern.databinding.ItemrvUserListBinding

/**
 * Created by rivaldy on Sep/07/2020.
 * Find me on my lol Github :D -> https://github.com/im-o
 */

class AdapterUserList(private val userData: MutableList<Data>, private val listener: (Data) -> Unit): RecyclerView.Adapter<AdapterUserList.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemrvUserListBinding.inflate(layoutInflater)
        return UserViewHolder(binding)
    }

    override fun getItemCount() = userData.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bindItem(userData[position], listener)
    }

    class UserViewHolder(private val binding: ItemrvUserListBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindItem(user: Data, listener: (Data) -> Unit) {
            val idUser = "Id User : ${user.id.toString()}"
            val emailUser = "Email user : ${user.email}"
            val firstName = "First name : ${user.first_name}"
            val lastName = "Last Name : ${user.last_name}"

            binding.apply {
                userIdTV.text = idUser
                userEmailTV.text = emailUser
                firstNameTV.text = firstName
                lastNameTV.text = lastName

                root.setOnClickListener {
                    listener(user)
                }
            }
        }
    }
}