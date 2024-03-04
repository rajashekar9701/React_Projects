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
    	existingPersonalInfo.setGovernmentId(personalInfo.getGovernmentId());
    	existingPersonalInfo.setAddress(personalInfo.getAddress());
    	existingPersonalInfo.setPhoneNo(personalInfo.getPhoneNo());
    	existingPersonalInfo.setBirthDate(personalInfo.getBirthDate());
    	existingPersonalInfo.setPersonalEmail(personalInfo.getPersonalEmail());
    	existingPersonalInfo.setMaritalStatus(personalInfo.getMaritalStatus());

    	existingPersonalInfo.setAlternatePhoneNo(personalInfo.getAlternatePhoneNo());
    	EmployeeDetails updatedPersonalInfo = personalInformationRepository.save(existingPersonalInfo);
        return updatedPersonalInfo;
    }

    @Override
    public void deletePersonalInfo(Long employeeId) {
    	personalInformationRepository.deleteById(employeeId);
    }
    
    
    @Override
    public Long getEmployeeIdByEmail(String userName) {
        Long employeeId = personalInformationRepository.findEmployeeIdByEmail(userName);
        return employeeId;
    }
}
