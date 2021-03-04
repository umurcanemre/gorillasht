package com.umurcan.gorillas.domain

import com.expediagroup.graphql.annotations.GraphQLDescription
import com.expediagroup.graphql.annotations.GraphQLIgnore

@GraphQLDescription("Application user records")
data class User(val firstName:String, val lastName:String, val userName:String, @GraphQLIgnore val password:String, val email:String)