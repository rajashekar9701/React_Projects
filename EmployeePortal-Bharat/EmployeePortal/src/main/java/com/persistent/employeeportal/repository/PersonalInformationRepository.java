package com.persistent.employeeportal.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.persistent.employeeportal.entity.EmployeeDetails;


@Repository
public interface PersonalInformationRepository extends CrudRepository<EmployeeDetails, Long> {
	
	@Query("select employeeId from EmployeeDetails where email= :emailId")
	long findEmployeeIdByEmployeeEmailId(@Param("emailId") String emailId);

	
}
