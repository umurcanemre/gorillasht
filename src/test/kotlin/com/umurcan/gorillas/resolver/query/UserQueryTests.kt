package com.umurcan.gorillas.resolver.query

import com.umurcan.gorillas.mockdata.UserHelper
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UserQueryTests {
    lateinit var userQuery : UserQuery
    val exampleCount = 5

    @BeforeEach
    fun prepareTest() {
        userQuery = UserQuery(UserHelper.getUser(exampleCount))
    }

    @Test
    fun testGetAll() {
        runBlocking<Unit> {
            val result = userQuery.users()

            Assertions.assertNotEquals(null, result)
            Assertions.assertEquals(exampleCount, result!!.size)
            for (i in 1..exampleCount) {
                Assertions.assertEquals("firstName$i", result[i-1].firstName)
            }
        }
    }

    @Test
    fun testGetByUsername() {
        runBlocking<Unit> {
            for (i in 1..exampleCount) {
                val result = userQuery.getUsersByFirstName("firstName$i")
                Assertions.assertNotEquals(null, result)
                Assertions.assertEquals(1, result!!.size)
            }
        }
    }

}