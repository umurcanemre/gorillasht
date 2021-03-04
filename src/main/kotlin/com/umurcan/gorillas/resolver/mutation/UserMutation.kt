package com.umurcan.gorillas.resolver.mutation

import com.expediagroup.graphql.spring.operations.Mutation
import com.umurcan.gorillas.domain.User
import com.umurcan.gorillas.exception.FailedAuthenticationException
import graphql.execution.DataFetcherResult
import io.fusionauth.jwt.Signer
import io.fusionauth.jwt.domain.JWT
import io.fusionauth.jwt.hmac.HMACSigner
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.ZoneOffset
import java.time.ZonedDateTime

@Service
class UserMutation(@Autowired private val users:List<User>) : Mutation{
    // Build an HMAC signer using a SHA-256 hash
    val signer: Signer = HMACSigner.newSHA256Signer("staticSecretText")
    private val logger = KotlinLogging.logger {}

    suspend fun auth(userName : String, password : String) : DataFetcherResult<String?> {
        val user : User? = users.find { it.userName == userName && it.password == it.password }
        if (user != null) {
            return DataFetcherResult.newResult<String?>().data(generateToken(user)).build()
        }
        logger.warn { "unsuccessful authentication attempt" }
        return DataFetcherResult.newResult<String?>().error(FailedAuthenticationException()).build()
    }

    private suspend fun generateToken(user : User) : String{
        val jwt = JWT().setIssuer("www.nosuchwebpage.com")
            .setIssuedAt(ZonedDateTime.now(ZoneOffset.UTC))
            .setSubject(user.userName)
            .setExpiration(ZonedDateTime.now(ZoneOffset.UTC).plusMinutes(60))

        //register token to be checked in later requests
        return JWT.getEncoder().encode(jwt, signer)
    }
}