
package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dto.LoginRequest;
import com.example.demo.entity.User;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<User> registerUser(@RequestBody User user) {
		User newUser = userService.registerUser(user);
		return ResponseEntity.ok(newUser);
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));
			String token = jwtUtil.generateToken(authentication);
			return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, "Bearer " + token).body("Login successful");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body("Login failed");
		}
	}

}