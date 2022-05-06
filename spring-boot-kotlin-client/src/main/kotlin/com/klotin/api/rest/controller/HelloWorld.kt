package com.klotin.api.rest.controller

import com.klotin.api.rest.client.UserClient
import com.klotin.api.rest.entity.User
import org.springframework.http.HttpHeaders
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloWorld(val iUserClient: UserClient) {

    @GetMapping("/user")
    fun helloUser(@RequestHeader(name = HttpHeaders.AUTHORIZATION) token:String) = iUserClient.getUser(token)

    @GetMapping("/admin")
    fun helloAdmin(@RequestHeader(name = HttpHeaders.AUTHORIZATION) token:String) = iUserClient.getAdmin(token)

    @GetMapping("/user-entity")
    fun helloUserEntity() = User(1, "Daniel", "Angel")
}