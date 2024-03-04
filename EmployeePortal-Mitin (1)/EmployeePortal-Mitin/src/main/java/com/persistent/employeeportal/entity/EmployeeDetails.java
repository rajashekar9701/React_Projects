package com.persistent.employeeportal.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

@Table(name = "employee_details")
@Entity
public class EmployeeDetails implements Serializable {
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
	private String firstName;

	@NotNull
	private String lastName;

	private String address;

	@NotNull
	private Long phoneNo;

	private Long alternatePhoneNo;

	@Email
	private String personalEmail;

	@Email
	@NotNull
	private String email;
	
	@NotNull
	private String password;

	@NotNull
	private String governmentId;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull
	private Date birthDate;

	private String maritalStatus;
	
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(Long phoneNo) {
		this.phoneNo = phoneNo;
	}

	public Long getAlternatePhoneNo() {
		return alternatePhoneNo;
	}

	public void setAlternatePhoneNo(Long alternatePhoneNo) {
		this.alternatePhoneNo = alternatePhoneNo;
	}

	public String getPersonalEmail() {
		return personalEmail;
	}

	public void setPersonalEmail(String personalEmailId) {
		this.personalEmail = personalEmailId;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGovernmentId() {
		return governmentId;
	}

	public void setGovernmentId(String governmentId) {
		this.governmentId = governmentId;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/*@Override
	public String toString() {
		return "EmployeeDetails [Id=" + Id + ", employeeId=" + employeeId + ", firstName=" + firstName + ", lastName="
				+ lastName + ", address=" + address + ", phoneNo=" + phoneNo + ", alternatePhoneNo=" + alternatePhoneNo
				+ ", personalEmail=" + personalEmail + ", email=" + email + ", password=" + password + ", governmentId="
				+ governmentId + ", birthDate=" + birthDate + ", maritalStatus=" + maritalStatus + "]";
	}*/

	@Override
	public String toString() {
		return "EmployeeDetails [employeeId=" + employeeId + ", firstName=" + firstName + ", lastName="
				+ lastName + ", address=" + address + ", phoneNo=" + phoneNo + ", alternatePhoneNo=" + alternatePhoneNo
				+ ", personalEmail=" + personalEmail + ", email=" + email + ", password=" + password + ", governmentId="
				+ governmentId + ", birthDate=" + birthDate + ", maritalStatus=" + maritalStatus + "]";
	}

	

}