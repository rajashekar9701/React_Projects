package com.persistent.employeeportal.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Date;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Table(name = "company_details")
@Entity
public class CompanyDetails implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	//@Id
//	private Long Id;
	
	
	

	@NotNull
	@Id
	private Long employeeId;

	@NotNull
	private String title;

	private String managerName;

	@NotNull
	private String workLocation;

	private Long workPhone;

	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
	private Date joiningDate;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "employee_id")
    private EmployeeDetails employeeDetails;
	

	

	//	public Long getId() {
//		return Id;
//	}
//
//	public void setId(Long id) {
//		this.Id = id;
//	}
	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getWorkLocation() {
		return workLocation;
	}

	public void setWorkLocation(String workLocation) {
		this.workLocation = workLocation;
	}

	public Long getWorkPhone() {
		return workPhone;
	}

	public void setWorkPhone(Long workPhone) {
		this.workPhone = workPhone;
	}

	public Date getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}

	public EmployeeDetails getEmployeeDetails() {
		return employeeDetails;
	}

	public void setEmployeeDetails(EmployeeDetails employeeDetails) {
		this.employeeDetails = employeeDetails;
	}
	
	@Override
	public String toString() {
		return "CompanyDetails [employeeId=" + employeeId + ", title=" + title + ", managerName=" + managerName
				+ ", workLocation=" + workLocation + ", workPhone=" + workPhone + ", joiningDate=" + joiningDate
				+ ", employeeDetails=" + employeeDetails + "]";
	}

	

}