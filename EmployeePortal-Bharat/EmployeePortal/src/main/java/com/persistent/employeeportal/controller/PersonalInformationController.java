package com.persistent.employeeportal.controller;

import java.time.LocalDateTime;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.persistent.employeeportal.entity.EmployeeDetails;
import com.persistent.employeeportal.exception.EmployeePortalException;
import com.persistent.employeeportal.exception.ResourceNotFoundException;
import com.persistent.employeeportal.repository.PersonalInformationRepository;
import com.persistent.employeeportal.service.PersonalInformationService;

import jakarta.validation.Valid;
//import net.javaguides.springboot.model.Employee;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PersonalInformationController {
	
	final String urlPath = "/employeeportal-service/personalinfo";

	@Autowired
	private PersonalInformationService personalInformationService;
	
	// Add Personal Info
    @PostMapping(path = urlPath)
    public ResponseEntity<Object> addPersonalInfo(@Valid @RequestBody EmployeeDetails personalInformation)
    {
    	try{
            EmployeeDetails personalInformationResponse = personalInformationService.createPersonalInfo(personalInformation);
    		return new ResponseEntity<Object>(personalInformationResponse, HttpStatus.OK);
	   }catch (Exception e) {
		   e.printStackTrace();
		   return new ResponseEntity<Object>(new EmployeePortalException("Couldn't save Personal Information ", e.getMessage(), LocalDateTime.now()),HttpStatus.BAD_REQUEST);
	   }
    }
//	@GetMapping(path = urlPath +"/{employeeId}")
//	public ResponseEntity<EmployeeDetails> getEmployeeById(@PathVariable Long employeeId) {
//		EmployeeDetails employee = personalInformationRepository.findById(employeeId)
//				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + employeeId));
//		return ResponseEntity.ok(employee);
//	}
    @GetMapping(path = urlPath +"/{employeeId}")
    public ResponseEntity<Object> getPersonalDetails(@PathVariable("employeeId") Long employeeId){
    	try {
    		EmployeeDetails empDetails = personalInformationService.getPersonalInfoById(employeeId);
    		return new ResponseEntity<Object>(empDetails, HttpStatus.OK);
	    } catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(new EmployeePortalException("Couldn't add Personal Information", e.getMessage(), LocalDateTime.now()),HttpStatus.BAD_REQUEST);
		}
    }
    		
	//Get All Personal Info 
    @GetMapping(path = urlPath)
    public List<EmployeeDetails> getAllPersonalInfo()
    {
        return personalInformationService.getAllPersonalInfo();
    }
    
    //Build Update User REST API
    @PutMapping(path = urlPath +"/{employeeId}")

    public ResponseEntity<Object> updatePersonalInfo(@PathVariable("employeeId") Long employeeId,
                                           @RequestBody EmployeeDetails personalInformation){
    	try {
	    	personalInformation.setEmployeeId(employeeId);
	        EmployeeDetails updatedPersonalInfo = personalInformationService.updatePersonalInfo(personalInformation, employeeId);
	        return new ResponseEntity<Object>(updatedPersonalInfo, HttpStatus.OK);
	    } catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return new ResponseEntity<Object>(new EmployeePortalException("Couldn't update Personal Information", "Wrong Employee Id", LocalDateTime.now()),HttpStatus.BAD_REQUEST);
			
		}
    }

    // Build Delete User REST API
    @DeleteMapping(path = urlPath +"/{employeeId}")
    public ResponseEntity<Object> deletePersonalDetails(@PathVariable("employeeId") Long employeeId){
    	try {
    		personalInformationService.deletePersonalInfo(employeeId);
    		return new ResponseEntity<Object>("Personal Information successfully deleted!", HttpStatus.OK);
	    } catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(new EmployeePortalException("Couldn't delete Personal Information", e.getMessage(), LocalDateTime.now()),HttpStatus.BAD_REQUEST);
		}
    }
}
