package com.umurcan.gorillas.domain

import com.expediagroup.graphql.annotations.GraphQLDescription

@GraphQLDescription("Application user records")
data class User(val firstName:String, val lastName:String, val userName:String, val password:String, val email:String)