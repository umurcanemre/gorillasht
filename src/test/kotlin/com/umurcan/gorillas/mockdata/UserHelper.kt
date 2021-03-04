package com.umurcan.gorillas.mockdata

import com.umurcan.gorillas.domain.User

class UserHelper {
    companion object {
        fun getUser(count : Int) : List<User> {
            val users = ArrayList<User>()

            for(i in 1..count) {
                users.add(User("firstName$i", "lastName$i", "userName$i", "password$i", "email$i"))
            }

            return users
        }
    }
}