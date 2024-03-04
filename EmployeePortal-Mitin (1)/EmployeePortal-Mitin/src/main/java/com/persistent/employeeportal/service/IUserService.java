package com.persistent.employeeportal.service;



import com.persistent.employeeportal.entity.*;
import org.springframework.http.ResponseEntity;


public interface IUserService {
   //ResponseEntity<?> register (RegisterDto registerDto);
 //  ResponseEntity<BearerToken> authenticate(LoginDto loginDto);

	JwtResponse authenticate(LoginDto loginDto);
   ResponseEntity<?> register (RegisterDto registerDto);

    ResponseEntity<?> logout(LogoutDto logoutDto);
  

  User saverUser (User user) ;
}