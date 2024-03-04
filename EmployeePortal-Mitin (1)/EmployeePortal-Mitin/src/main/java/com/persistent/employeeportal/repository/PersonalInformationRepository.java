package com.persistent.employeeportal.repository;

import com.persistent.employeeportal.entity.EmployeeDetails;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PersonalInformationRepository extends CrudRepository<EmployeeDetails, Long> {

	
}
