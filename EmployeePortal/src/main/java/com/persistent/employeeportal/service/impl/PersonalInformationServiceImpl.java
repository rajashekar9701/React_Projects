package com.persistent.employeeportal.service.impl;

import com.persistent.employeeportal.entity.EmployeeDetails;
import com.persistent.employeeportal.service.PersonalInformationService;
import com.persistent.employeeportal.repository.PersonalInformationRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonalInformationServiceImpl implements PersonalInformationService {

	@Autowired
	private PersonalInformationRepository personalInformationRepository;

    @Override
    public EmployeeDetails createPersonalInfo(EmployeeDetails personalInfo){
        return personalInformationRepository.save(personalInfo);
    }

    @Override
    public EmployeeDetails getPersonalInfoById(Long employeeId) {
        Optional<EmployeeDetails> optionalUser = personalInformationRepository.findById(employeeId);
        return optionalUser.get();
    }

    @Override
    public List<EmployeeDetails> getAllPersonalInfo() {
        return (List<EmployeeDetails>) personalInformationRepository.findAll();
    }

    @Override
    public EmployeeDetails updatePersonalInfo(EmployeeDetails personalInfo, Long employeeId) {
    	EmployeeDetails existingPersonalInfo = personalInformationRepository.findById(employeeId).get();
    	existingPersonalInfo.setFirstName(personalInfo.getFirstName());
    	existingPersonalInfo.setLastName(personalInfo.getLastName());
    	existingPersonalInfo.setAddress(personalInfo.getAddress());
    	existingPersonalInfo.setBirthDate(personalInfo.getBirthDate());
    	existingPersonalInfo.setEmail(personalInfo.getEmail());
    	existingPersonalInfo.setPhoneNo(personalInfo.getPhoneNo());
    	existingPersonalInfo.setGovernmentId(personalInfo.getGovernmentId());
    	existingPersonalInfo.setMaritalStatus(personalInfo.getMaritalStatus());
    	existingPersonalInfo.setPersonalEmail(personalInfo.getPersonalEmail());
    	EmployeeDetails updatedPersonalInfo = personalInformationRepository.save(existingPersonalInfo);
        return updatedPersonalInfo;
    }

    @Override
    public void deletePersonalInfo(Long employeeId) {
    	personalInformationRepository.deleteById(employeeId);
    }
    
}
