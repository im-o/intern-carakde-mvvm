package com.example.mvvmsampleappintern.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mvvmsampleappintern.data.db.entities.User

/**
 * Created by rivaldy on Sep/01/2020.
 * Find me on my lol Github :D -> https://github.com/im-o
 */

@Database(
    entities = [User::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserDao

    companion object {
        @Volatile
        private var instances: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instances ?: synchronized(LOCK){
            instances?:buildDatabase(context).also {
                instances = it
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "MyDatabase.db").build()
    }
}