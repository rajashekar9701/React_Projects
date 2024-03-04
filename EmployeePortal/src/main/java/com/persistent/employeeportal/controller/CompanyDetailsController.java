package com.persistent.employeeportal.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.persistent.employeeportal.entity.CompanyDetails;
import com.persistent.employeeportal.exception.EmployeePortalException;
import com.persistent.employeeportal.service.CompanyDetailsService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CompanyDetailsController {
	
	final String urlPath = "/employeeportal-service/companydetails";

	@Autowired
	private CompanyDetailsService companyDetailsService;
	
	// Add Personal Info
    @PostMapping(path = urlPath)
    public ResponseEntity<Object> addCompanyDetails(@Valid @RequestBody CompanyDetails companyDetails)
    {
    	try {
    		CompanyDetails personalInformationResponse = companyDetailsService.createCompanyDetails(companyDetails);
    		return new ResponseEntity<Object>(personalInformationResponse, HttpStatus.OK);
	    } catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return new ResponseEntity<Object>(new EmployeePortalException("Couldn't save Company Details", e.getMessage(), LocalDateTime.now()),HttpStatus.BAD_REQUEST);
		}
    }
	
	//Get All Personal Info 
    @GetMapping(path = urlPath)
    public List<CompanyDetails> getCompanyDetails()
    {
        return companyDetailsService.getCompanyDetails();
    }
    
    //Build Update User REST API
    @PutMapping(path = urlPath + "/{employeeId}")
    public ResponseEntity<Object> updatePersonalInfo(@PathVariable("employeeId") Long employeeId,
                                           @RequestBody CompanyDetails companyDetails){
    	CompanyDetails updatedPersonalInfo =  null;
    	try {
	    	updatedPersonalInfo = companyDetailsService.updateCompanyDetails(companyDetails, employeeId);
	        return new ResponseEntity<Object>(updatedPersonalInfo, HttpStatus.OK);
	    } catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return new ResponseEntity<Object>(new EmployeePortalException("Couldn't update Company Details", "Wrong Employee Id", LocalDateTime.now()),HttpStatus.BAD_REQUEST);
			
		}
    }

    // Build Delete User REST API
    @DeleteMapping(path = urlPath + "/{employeeId}")
    public ResponseEntity<Object> deletePersonalDetails(@PathVariable("employeeId") Long employeeId){
    	try {
    		companyDetailsService.deleteCompanyDetails(employeeId);
    		return new ResponseEntity<Object>("Company Details of Employee successfully deleted!", HttpStatus.OK);
	    } catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(new EmployeePortalException("Couldn't delete Company Details", e.getMessage(), LocalDateTime.now()),HttpStatus.BAD_REQUEST);
		}
    }
}
