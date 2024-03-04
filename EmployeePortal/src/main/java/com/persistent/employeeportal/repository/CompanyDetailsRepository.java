package com.persistent.employeeportal.repository;

import com.persistent.employeeportal.entity.CompanyDetails;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CompanyDetailsRepository extends CrudRepository<CompanyDetails, Long> {

	
}
