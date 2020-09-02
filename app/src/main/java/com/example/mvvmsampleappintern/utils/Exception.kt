package com.example.mvvmsampleappintern.utils

import java.io.IOException

/**
 * Created by rivaldy on Sep/01/2020.
 * Find me on my lol Github :D -> https://github.com/im-o
 */

class ApiException(error: String): IOException(error)
class NoInternetException(message: String) : IOException(message)