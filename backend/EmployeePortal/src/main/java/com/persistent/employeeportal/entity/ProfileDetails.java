package com.persistent.employeeportal.entity;

import java.util.Arrays;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "profile_details")
public class ProfileDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "document_id")
	private Long documentId;

	@Column(name = "document_name")
	private String documentName;

	@Lob
	@Column(name = "document_file")
	private byte[] documentFile;
	
	@Column(name = "document_filetype")
	private String fileType;

	@Column(name = "employee_id")
	@NotNull(message = "Employee Id is required field.")
	private Long employeeId;

	public ProfileDetails() {
		super();
	}	

	public ProfileDetails(Long documentId, String documentName, byte[] documentFile, String fileType, Long employeeId) {
		super();
		this.documentId = documentId;
		this.documentName = documentName;
		this.documentFile = documentFile;
		this.fileType = fileType;
		this.employeeId = employeeId;
	}

	public Long getDocumentId() {
		return documentId;
	}

	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public byte[] getDocumentFile() {
		return documentFile;
	}

	public void setDocumentFile(byte[] documentFile) {
		this.documentFile = documentFile;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	@Override
	public String toString() {
		return "ProfileDetails [documentId=" + documentId + ", documentName=" + documentName + ", documentFile="
				+ Arrays.toString(documentFile) + ", fileType=" + fileType + ", employeeId=" + employeeId + "]";
	}

}
