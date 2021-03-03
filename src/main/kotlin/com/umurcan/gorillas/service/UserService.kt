package com.umurcan.gorillas.service

import com.umurcan.gorillas.domain.User

interface UserService {
    suspend fun getAll() : List<User>
    suspend fun get(userName : String, password : String) : List<User>?
    suspend fun get(userName: String) : List<User>?
}
