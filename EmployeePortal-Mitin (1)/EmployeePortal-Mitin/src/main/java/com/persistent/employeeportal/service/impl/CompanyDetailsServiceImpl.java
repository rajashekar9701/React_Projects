package com.persistent.employeeportal.service.impl;

import com.persistent.employeeportal.entity.CompanyDetails;
import com.persistent.employeeportal.service.CompanyDetailsService;
import com.persistent.employeeportal.repository.CompanyDetailsRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyDetailsServiceImpl implements CompanyDetailsService {

	@Autowired
	private CompanyDetailsRepository companyDetailsRepository;

    @Override
    public CompanyDetails createCompanyDetails(CompanyDetails personalInfo){
        return companyDetailsRepository.save(personalInfo);
    }

    @Override
    public CompanyDetails getCompanyDetailsById(Long employeeId) {
        Optional<CompanyDetails> optionalUser = companyDetailsRepository.findById(employeeId);
        return optionalUser.get();
    }

    @Override
    public List<CompanyDetails> getCompanyDetails() {
        return (List<CompanyDetails>) companyDetailsRepository.findAll();
    }

    @Override
    public CompanyDetails updateCompanyDetails(CompanyDetails companyDetails, Long employeeId) {
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
    public void deleteCompanyDetails(Long employeeId) {
    	companyDetailsRepository.deleteById(employeeId);
    }
    
}
