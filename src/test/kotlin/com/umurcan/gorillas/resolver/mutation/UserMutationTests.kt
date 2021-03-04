package com.umurcan.gorillas.resolver.mutation

import com.umurcan.gorillas.exception.FailedAuthenticationException
import com.umurcan.gorillas.mockdata.UserHelper
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UserMutationTests {


    lateinit var userMutation: UserMutation
    val exampleCount = 5

    @BeforeEach
    fun prepareTest() {
        userMutation = UserMutation(UserHelper.getUser(exampleCount))
    }

    @Test
    fun authTest_success() {
        runBlocking {
            for(i in 1..exampleCount){
                val authResponse = userMutation.auth("userName$i", "password$i")
                Assertions.assertNotEquals(null, authResponse)
                Assertions.assertNotEquals(null, authResponse.data)
                Assertions.assertTrue(authResponse.errors.isEmpty())
                val authString = authResponse.data
                Assertions.assertNotEquals(0, authString!!.length)
                Assertions.assertTrue(authString.contains('.'))// jwt strings contain 2 dots
            }
        }
    }

    @Test
    fun authTest_fail() {
        runBlocking {
            val authResponse = userMutation.auth("nonexistent username", "and/or.wrong.password")
            Assertions.assertNotEquals(null, authResponse)
            Assertions.assertNotEquals(null, authResponse.errors)
            Assertions.assertEquals(null, authResponse.data)
            Assertions.assertEquals(1, authResponse.errors.size)
            Assertions.assertTrue(authResponse.errors[0] is FailedAuthenticationException)
        }
    }
}