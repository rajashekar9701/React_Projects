package com.persistent.employeeportal.repository;

import com.persistent.employeeportal.entity.CompanyDetails;
import com.persistent.employeeportal.entity.EmployeeDetails;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface CompanyDetailsRepository extends CrudRepository<CompanyDetails, Long> {

	
	
}
