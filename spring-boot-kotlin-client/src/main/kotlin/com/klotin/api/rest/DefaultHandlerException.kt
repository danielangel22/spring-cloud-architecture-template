package com.klotin.api.rest

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import feign.FeignException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MissingRequestHeaderException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.net.ConnectException

@RestControllerAdvice
class DefaultHandlerException {

    @ExceptionHandler
    fun parameterNotFound(ex: MissingServletRequestParameterException): ResponseEntity<Map<String, String>> {
        return ResponseEntity(
            mapOf(
                "message" to "Missing parameter",
                "error" to ex.message.toString()
            ), HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler
    fun parameterNotFound(ex: MissingRequestHeaderException): ResponseEntity<Map<String, String>> {
        return ResponseEntity(
            mapOf(
                "message" to "Missing parameter",
                "error" to ex.message.toString()
            ), HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler
    fun connectException(ex: ConnectException): ResponseEntity<Map<String, String>> {
        return ResponseEntity(
            mapOf(
                "message" to "Could not connect to client",
                "error" to ex.message.toString()
            ), HttpStatus.FAILED_DEPENDENCY
        )
    }

    @ExceptionHandler
    fun feingException(ex: FeignException): ResponseEntity<MutableMap<Any, Any>> {
        val response = ex.responseBody().get().array()
        val resp = ObjectMapper().readValue<MutableMap<Any, Any>>(String(response))
        return ResponseEntity(resp, HttpStatus.valueOf(ex.status()))
    }

}