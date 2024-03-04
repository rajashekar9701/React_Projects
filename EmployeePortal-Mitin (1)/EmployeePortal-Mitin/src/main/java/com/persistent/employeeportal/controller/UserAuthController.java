package com.persistent.employeeportal.controller;

import com.persistent.employeeportal.entity.LogoutDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.persistent.employeeportal.entity.LoginDto;
import com.persistent.employeeportal.entity.RegisterDto;
import com.persistent.employeeportal.service.IUserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserAuthController {

	@Autowired
	private IUserService iUserService;
	
	final  String urlPath = "/employeeportal-service/employee_auth";
	
	
	
	@PostMapping(path = urlPath +"/signup")
	public ResponseEntity<?> register(@RequestBody RegisterDto registerDto) {
		
		iUserService.register(registerDto);
		return new ResponseEntity<>("Employee registered successfully", HttpStatus.CREATED);
	}

	@PostMapping(path = urlPath +"/signin")
	public ResponseEntity<?> authenticate(@RequestBody LoginDto loginDto) {

		return new ResponseEntity<>(iUserService.authenticate(loginDto), HttpStatus.CREATED);

	}

	@PostMapping(path = urlPath +"/logout")
	public ResponseEntity<?> logoutUser(@RequestBody LogoutDto logoutDto) {
		SecurityContextHolder.clearContext();
		iUserService.logout(logoutDto);
		return ResponseEntity.ok("Logout successful");
	}

	@GetMapping(path = urlPath +"/test")
	public String authenticateTest() {
		return "Restricted Message";
	}

}