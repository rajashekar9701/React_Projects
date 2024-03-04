package com.persistent.employeeportal.controller;

import java.time.LocalDateTime;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.persistent.employeeportal.entity.EmployeeDetails;
import com.persistent.employeeportal.exception.EmployeePortalException;
import com.persistent.employeeportal.service.PersonalInformationService;

import jakarta.validation.Valid;

//import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PersonalInformationController {
	
	final String urlPath = "/employeeportal-service/personalinfo";

	@Autowired
	private PersonalInformationService personalInformationService;
	
	// Add Employee Personal Info
    /*@PostMapping(path = urlPath)
    @PreAuthorize("#username == authentication.name")
    public ResponseEntity<Object> addPersonalInfo(@Valid @RequestBody EmployeeDetails personalInformation)
    {
    	try{
            EmployeeDetails personalInformationResponse = personalInformationService.createPersonalInfo(personalInformation);
    		return new ResponseEntity<Object>(personalInformationResponse, HttpStatus.OK);
	   }catch (Exception e) {
		   e.printStackTrace();
		   return new ResponseEntity<Object>(new EmployeePortalException("Couldn't save Personal Information ", e.getMessage(), LocalDateTime.now()),HttpStatus.BAD_REQUEST);
	   }
    }*/
    
    @GetMapping(path = urlPath +"/{username}")
	@PreAuthorize("#username == authentication.name")
    public ResponseEntity<Object> getPersonalDetails(@PathVariable("username") String username){
    	try {
    		Long employeeId = personalInformationService.getEmployeeIdByEmail(username);
    		EmployeeDetails empDetails = personalInformationService.getPersonalInfoById(employeeId);
    		return new ResponseEntity<Object>(empDetails, HttpStatus.OK);
	    } catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(new EmployeePortalException("Couldn't add Personal Information", e.getMessage(), LocalDateTime.now()),HttpStatus.BAD_REQUEST);
		}
    }
    
    
    //Build Update User REST API
    @PutMapping(path = urlPath + "/{username}")
	@PreAuthorize("#username == authentication.name")
    public ResponseEntity<Object> updatePersonalInfo(@PathVariable("username") String username,
    		@Valid @RequestBody EmployeeDetails personalInformation){
    	try {
    		Long employeeId = personalInformationService.getEmployeeIdByEmail(username);    		
	        EmployeeDetails updatedPersonalInfo = personalInformationService.updatePersonalInfo(personalInformation, employeeId);
	        return new ResponseEntity<Object>(updatedPersonalInfo, HttpStatus.OK);
	    } catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return new ResponseEntity<Object>(new EmployeePortalException("Couldn't update Personal Information", "Wrong Employee Id", LocalDateTime.now()),HttpStatus.BAD_REQUEST);
			
		}
    }

}
