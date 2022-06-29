package com.klotin.api.rest.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.HttpHeaders
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader

@FeignClient(name = "user-service")
interface UserClient {

    @GetMapping("/user")
    fun getUser(@RequestHeader(name = HttpHeaders.AUTHORIZATION) token:String): String

    @GetMapping("/admin")
    fun getAdmin(@RequestHeader(name = HttpHeaders.AUTHORIZATION) token:String): String
}