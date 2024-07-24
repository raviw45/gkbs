package com.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

	
	@GetMapping("/message")
	public ResponseEntity<String> adminMessage(){
		return ResponseEntity.ok("Hello from the admin panel!!!");
	}
}
