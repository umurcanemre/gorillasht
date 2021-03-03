package com.umurcan.gorillas.mutation

import com.expediagroup.graphql.spring.operations.Mutation
import com.umurcan.gorillas.domain.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserMutation(@Autowired private val users:List<User>) : Mutation{
    suspend fun auth(userName : String, password : String) : String{
        if (users.any{ it.userName == userName && it.password == it.password })
            return UUID.randomUUID().toString()
        else
            throw Exception("UserName and password did not match");
    }
}