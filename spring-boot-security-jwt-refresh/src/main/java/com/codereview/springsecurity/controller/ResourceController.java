package com.codereview.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {

	@GetMapping("/user")
	public String getUser() {
		return "Hello User";
	}

	@GetMapping("/admin")
	public String getAdmin() {
		return "Hello Admin";
	}

}
