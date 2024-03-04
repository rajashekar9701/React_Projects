package com.persistent.employeeportal.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Table(name = "personal_information")
@Entity
public class PersonalInformation implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long employeeId;

	private String firstName;

	private String lastName;

	private String address;

	private Long homePhone;

	private Long cellPhone;

	private String personalEmailId;

	private String employeeEmailId;

	private String governmentId;

	private String birthDate;

	private String maritalStatus;

	private String spouseName;

	private Long spousePhoneNo;

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

	public Long getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(Long homePhone) {
		this.homePhone = homePhone;
	}

	public Long getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(Long cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getPersonalEmailId() {
		return personalEmailId;
	}

	public void setPersonalEmailId(String personalEmailId) {
		this.personalEmailId = personalEmailId;
	}

	public String getGovernmentId() {
		return governmentId;
	}

	public void setGovernmentId(String governmentId) {
		this.governmentId = governmentId;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getSpouseName() {
		return spouseName;
	}

	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}

	public Long getSpousePhoneNo() {
		return spousePhoneNo;
	}

	public void setSpousePhoneNo(Long spousePhoneNo) {
		this.spousePhoneNo = spousePhoneNo;
	}

	public String getEmployeeEmailId() {
		return employeeEmailId;
	}

	public void setEmployeeEmailId(String employeeEmailId) {
		this.employeeEmailId = employeeEmailId;
	}

	@Override
	public String toString() {
		return "PersonalInfomation [employeeId=" + employeeId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", address=" + address + ", homePhone=" + homePhone + ", cellPhone=" + cellPhone
				+ ", personalEmailId=" + personalEmailId + ", employeeEmailId=" + employeeEmailId + ", governmentId="
				+ governmentId + ", birthDate=" + birthDate + ", maritalStatus=" + maritalStatus + ", spouseName="
				+ spouseName + ", spousePhoneNo=" + spousePhoneNo + "]";
	}

}