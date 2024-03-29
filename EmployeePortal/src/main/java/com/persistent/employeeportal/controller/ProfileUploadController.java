package com.persistent.employeeportal.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.persistent.employeeportal.exception.FileNotFoundException;
import com.persistent.employeeportal.exception.FileStorageException;
import com.persistent.employeeportal.exception.MaxUploadSizeExceededException;
import com.persistent.employeeportal.exception.ResourceNotFoundException;
import com.persistent.employeeportal.service.IProfileService;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.persistent.employeeportal.entity.ProfileDetails;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProfileUploadController {

	@Autowired
	private IProfileService iProfileService;
	
	final String urlPath = "/employeeportal-service/profile";

	public ProfileUploadController(IProfileService iProfileService) {
		super();
		this.iProfileService = iProfileService;
	}

	private final static String CONTENT_PDF = "application/pdf";
	private final static String CONTENT_DOCX = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";

	@PostMapping(value = urlPath + "/{empId}")
	public ResponseEntity<?> uploadToDatabase(@PathVariable("empId") Long employeeId, @RequestParam MultipartFile documentFile)
			throws MaxUploadSizeExceededException, FileStorageException, ResourceNotFoundException, Exception {
		try {			
			validateFile(documentFile); // File Validations

			ProfileDetails profileDetails = setProfileDetails(employeeId, documentFile);

			ProfileDetails profileDetailsResponse = iProfileService.fileUpload(profileDetails);
			Map<String, String> map = new HashMap<>();
			map = setResponse(HttpStatus.CREATED, "Resume uploaded successfully.",
					profileDetailsResponse.getDocumentName());

			return new ResponseEntity<>(map, HttpStatus.CREATED);

		} catch (MaxUploadSizeExceededException error) {
			throw new MaxUploadSizeExceededException(error.getMessage());
		} catch (FileStorageException error) {
			throw new FileStorageException(error.getMessage());
		} catch (ResourceNotFoundException error) {
			throw new ResourceNotFoundException(error.getMessage());
		} catch (Exception error) {
			throw new Exception(error.getMessage());
		}
	}	
	
	@PutMapping(value = urlPath + "/{empId}")
	public ResponseEntity<?> updateProfile(@PathVariable("empId") Long employeeId, @RequestParam MultipartFile documentFile)
			throws MaxUploadSizeExceededException, FileStorageException, ResourceNotFoundException, Exception {
		try {
			validateFile(documentFile); // File Validations
			
			ProfileDetails profileDetails = setProfileDetails(employeeId, documentFile);

			ProfileDetails updateProfileResponse = iProfileService.updateProfile(profileDetails);

			Map<String, String> map = new HashMap<>();
			
			map = setResponse(HttpStatus.NO_CONTENT, "Resume updated successfully.", updateProfileResponse.getDocumentName());

			return new ResponseEntity<>(map, HttpStatus.OK);
			
		} catch (MaxUploadSizeExceededException error) {
			throw new MaxUploadSizeExceededException(error.getMessage());
		} catch (FileStorageException error) {
			throw new FileStorageException(error.getMessage());
		} catch (ResourceNotFoundException error) {
			throw new ResourceNotFoundException(error.getMessage());
		} catch (Exception error) {
			throw new Exception(error.getMessage());
		}
	}
	 
	 
	@GetMapping(urlPath + "/downloadFile/{empId}")
	public ResponseEntity<Resource> downloadFile(@PathVariable("empId") Long employeeId) throws FileNotFoundException, Exception {
		try {
			ProfileDetails profileDetails = iProfileService.downloadProfile(employeeId);

			return ResponseEntity.ok().contentType(MediaType.parseMediaType(profileDetails.getFileType()))
					.header(HttpHeaders.CONTENT_DISPOSITION,
							"attachment; filename=\"" + profileDetails.getDocumentName() + "\"")
					.body(new ByteArrayResource(profileDetails.getDocumentFile()));
		} catch (FileNotFoundException error) {
			throw new FileNotFoundException(error.getMessage());
		} catch (Exception error) {
			throw new Exception(error.getMessage());
		}
	}

	@GetMapping(urlPath + "/filename/{empId}")
	public ResponseEntity<String> fileName(@PathVariable("empId") Long employeeId) throws FileNotFoundException, Exception {
		try {
			String fileName = iProfileService.getFileName(employeeId);

			return new ResponseEntity<String>(fileName, HttpStatus.OK);
			
		} catch (FileNotFoundException error) {
			throw new FileNotFoundException(error.getMessage());
		} catch (Exception error) {
			throw new Exception(error.getMessage());
		}
	}
	
	private ProfileDetails setProfileDetails(Long employeeId, MultipartFile documentFile) throws IOException {
		ProfileDetails profileDetails = new ProfileDetails();
		profileDetails.setDocumentName(documentFile.getOriginalFilename());
		profileDetails.setFileType(documentFile.getContentType());
		profileDetails.setDocumentFile(documentFile.getBytes());
		profileDetails.setEmployeeId(employeeId);
		return profileDetails;
	}
	
	private Map<String, String> setResponse(HttpStatus status, String message, String fileName) {
		Map<String, String> map = new HashMap<>();
		map.put("details", message);
		map.put("fileName", fileName);
		map.put("status", String.valueOf(status));
		map.put("timeStamp", String.valueOf(LocalDateTime.now()));
		return map;
	}
	
	private void validateFile(MultipartFile documentFile) throws MaxUploadSizeExceededException, Exception {
		if (documentFile.getSize() > 10000000) { // Below 10MB allowed
			throw new MaxUploadSizeExceededException("File size should not be greater than 10MB");
		} else if (!isValid(documentFile)) { // Check file format
			throw new Exception("Please upload valid file format(PDF or DOCX)");
		}
	}

	public Boolean isValid(MultipartFile documentFile) {

		if (documentFile.getContentType().equals(CONTENT_PDF) || documentFile.getContentType().equals(CONTENT_DOCX)) {
			return true;
		}
		return false;
	}

}
