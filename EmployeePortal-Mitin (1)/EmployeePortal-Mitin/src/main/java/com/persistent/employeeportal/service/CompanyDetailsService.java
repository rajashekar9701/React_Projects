package com.persistent.employeeportal.service;

import com.persistent.employeeportal.entity.CompanyDetails; 
import java.util.List;

public interface CompanyDetailsService {
	
	CompanyDetails createCompanyDetails(CompanyDetails personalInfo);

	CompanyDetails getCompanyDetailsById(Long employeeId);

    List<CompanyDetails> getCompanyDetails();

    CompanyDetails updateCompanyDetails(CompanyDetails companyDetails, Long employeeId);

    void deleteCompanyDetails(Long employeeId);
}