package com.umurcan.gorillas.query

import com.expediagroup.graphql.spring.operations.Query
import com.umurcan.gorillas.domain.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*

@Component
class UserQuery(@Autowired private val users:List<User>) : Query {
    suspend fun users() : List<User>? = users

    suspend fun getAll() : List<User>? {
        return users
    }

    suspend fun getUsersByFirstName(firstName:String) : List<User>? {
        return users.filter { it.firstName == firstName }
    }
}