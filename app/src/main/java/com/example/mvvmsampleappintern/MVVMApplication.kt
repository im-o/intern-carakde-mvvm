package com.example.mvvmsampleappintern

import android.app.Application
import com.example.mvvmsampleappintern.data.network.MyApi
import com.example.mvvmsampleappintern.data.network.NetworkConnectionInterceptor
import com.example.mvvmsampleappintern.data.repository.UserRepository
import com.example.mvvmsampleappintern.ui.auth.AuthViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

/**
 * Created by rivaldy on Sep/02/2020.
 * Find me on my lol Github :D -> https://github.com/im-o
 */

class MVVMApplication: Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@MVVMApplication))
        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { MyApi(instance()) }
        bind() from singleton { UserRepository(instance()) }
        bind() from singleton { AuthViewModelFactory(instance()) }
    }
}