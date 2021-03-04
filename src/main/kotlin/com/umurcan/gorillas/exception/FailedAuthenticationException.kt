package com.umurcan.gorillas.exception

import graphql.ErrorClassification
import graphql.ErrorType
import graphql.GraphQLError
import graphql.language.SourceLocation


class FailedAuthenticationException : GraphQLError {
    override fun getMessage(): String {
        return "No user with given credentials found"
    }

    override fun getLocations(): MutableList<SourceLocation>? {
        return null
    }

    override fun getErrorType(): ErrorClassification {
        return ErrorType.DataFetchingException
    }
}