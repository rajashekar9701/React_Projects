package com.persistent.employeeportal.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

@Table(name = "company_details")
@Entity
public class CompanyDetails implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	/*@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Long Id;*/
	
	@Id
	@NotNull
	private Long employeeId;

	@NotNull
	private String title;

	private String managerName;

	@NotNull
	private String workLocation;

	private Long workPhone;

	@DateTimeFormat(pattern = "YYYY-MM-dd")
	private Date joiningDate;
	

	/*public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}*/


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

	/*@Override
	public String toString() {
		return "CompanyDetails [Id=" + Id + ", employeeId=" + employeeId + ", title=" + title + ", managerName="
				+ managerName + ", workLocation=" + workLocation + ", workPhone=" + workPhone + ", joiningDate="
				+ joiningDate + "]";
	}*/
	
	@Override
	public String toString() {
		return "CompanyDetails [employeeId=" + employeeId + ", title=" + title + ", managerName="
				+ managerName + ", workLocation=" + workLocation + ", workPhone=" + workPhone + ", joiningDate="
				+ joiningDate + "]";
	}

	

}