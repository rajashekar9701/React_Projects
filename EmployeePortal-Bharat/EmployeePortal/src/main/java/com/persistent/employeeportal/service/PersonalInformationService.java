package com.persistent.employeeportal.service;

import com.persistent.employeeportal.entity.EmployeeDetails; 
import java.util.List;

public interface PersonalInformationService {
	
	EmployeeDetails createPersonalInfo(EmployeeDetails personalInfo);

	EmployeeDetails getPersonalInfoById(Long employeeId);

    List<EmployeeDetails> getAllPersonalInfo();

    EmployeeDetails updatePersonalInfo(EmployeeDetails personalInfo, Long employeeId);

    void deletePersonalInfo(Long employeeId);
}