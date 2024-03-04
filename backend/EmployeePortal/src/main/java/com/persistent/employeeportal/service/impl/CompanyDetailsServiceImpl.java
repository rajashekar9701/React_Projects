package com.persistent.employeeportal.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.persistent.employeeportal.entity.CompanyDetails;
import com.persistent.employeeportal.repository.CompanyDetailsRepository;
import com.persistent.employeeportal.repository.PersonalInformationRepository;
import com.persistent.employeeportal.service.CompanyDetailsService;

@Service
public class CompanyDetailsServiceImpl implements CompanyDetailsService {

	@Autowired
	private CompanyDetailsRepository companyDetailsRepository;
	
	@Autowired
	private PersonalInformationRepository personalInformationRepository;
	
	

    @Override
    public CompanyDetails createCompanyDetails(String username, CompanyDetails personalInfo){
    	Long employeeId = personalInformationRepository.findEmployeeIdByEmail(username);
    	CompanyDetails companyDetails = companyDetailsRepository.findById(employeeId).get();
        return companyDetailsRepository.save(companyDetails);
    }

    @Override
    public CompanyDetails getCompanyDetailsById(String username) {
    	Long employeeId = personalInformationRepository.findEmployeeIdByEmail(username);
    	Optional<CompanyDetails> optionalUser = companyDetailsRepository.findById(employeeId);
    	if(optionalUser.isPresent())
    		return optionalUser.get();
    	else
    		return new CompanyDetails();
    }

    @Override
    public List<CompanyDetails> getCompanyDetails() {
        return (List<CompanyDetails>) companyDetailsRepository.findAll();
    }

    @Override
    public CompanyDetails updateCompanyDetails(CompanyDetails companyDetails, String username) {
    	Long employeeId = personalInformationRepository.findEmployeeIdByEmail(username);
    	CompanyDetails existingCompanyDetails = companyDetailsRepository.findById(employeeId).get();
    	existingCompanyDetails.setJoiningDate(companyDetails.getJoiningDate());
    	existingCompanyDetails.setManagerName(companyDetails.getManagerName());
    	existingCompanyDetails.setWorkPhone(companyDetails.getWorkPhone());
    	existingCompanyDetails.setTitle(companyDetails.getTitle());
    	existingCompanyDetails.setWorkLocation(companyDetails.getWorkLocation());
    	CompanyDetails updatedCompanyDetails = companyDetailsRepository.save(existingCompanyDetails);
        return updatedCompanyDetails;
    }

    @Override
    public void deleteCompanyDetails(String username) {
    	Long employeeId = personalInformationRepository.findEmployeeIdByEmail(username);
    	companyDetailsRepository.deleteById(employeeId);
    }
    
}
