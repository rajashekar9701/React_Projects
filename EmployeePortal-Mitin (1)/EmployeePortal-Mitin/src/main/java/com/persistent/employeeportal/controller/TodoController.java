package com.persistent.employeeportal.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.persistent.employeeportal.entity.TodoDetails;
import com.persistent.employeeportal.exception.EmployeePortalException;
import com.persistent.employeeportal.repository.TodoRepository;
import com.persistent.employeeportal.service.impl.TodoServiceImpl;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TodoController {

	final String urlPath = "/employeeportal-service/todos";

	@Autowired
	TodoRepository repo;

	@Autowired
	TodoServiceImpl todoServiceImpl;

	@GetMapping(path = urlPath)
	public List<TodoDetails> getTodos() {
		return todoServiceImpl.findAllByEmployeeId(todoServiceImpl.getEmployeeId(""));
	}

	@GetMapping(path = urlPath + "/{createdDate}")
	public ResponseEntity<Object> findByCreatedDateAndEmployeeId(@PathVariable String createdDate) {
		try {
			System.out.println("@@@@@ created Date"+createdDate);
			return new ResponseEntity<Object>( todoServiceImpl.findByCreatedDateAndEmployeeId(createdDate, todoServiceImpl.getEmployeeId(""))
					, HttpStatus.OK);
		} catch (DateTimeParseException e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(
					new EmployeePortalException("Internal Server Error", "Text '" + createdDate + "' is not a valid date", LocalDateTime.now()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(
					new EmployeePortalException("Internal Server Error", e.getMessage(), LocalDateTime.now()),
					HttpStatus.BAD_REQUEST);
		} 
	}

	@PostMapping(path = urlPath)
	public ResponseEntity<Object> saveTodos(@RequestBody @Valid TodoDetails todo) {
		try {
			TodoDetails savedTodo = todoServiceImpl.saveTodo(todo);
			return new ResponseEntity<Object>(savedTodo, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return new ResponseEntity<Object>(
					new EmployeePortalException("Couldn't save Todo details", e.getMessage(), LocalDateTime.now()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping(path = urlPath)
	public ResponseEntity<Object> updateTodos(@RequestBody @Valid TodoDetails todo) {
		try {
			TodoDetails updatedTodo = todoServiceImpl.updateTodos(todo);
			return new ResponseEntity<Object>(updatedTodo, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return new ResponseEntity<Object>(
					new EmployeePortalException("Couldn't update Todo details", "Wrong Todo id", LocalDateTime.now()),
					HttpStatus.BAD_REQUEST);

		}
	}

	@DeleteMapping(path = urlPath + "/{todoId}")
	public ResponseEntity<Object> deleteTodos(@PathVariable long todoId) {
		try {
			todoServiceImpl.deleteTodoItem(todoId);
			return new ResponseEntity<Object>("Todo successfully deleted", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(
					new EmployeePortalException("Couldn't delete Todo details", e.getMessage(), LocalDateTime.now()),
					HttpStatus.BAD_REQUEST);
		}

	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleException(Exception e) {
		return new ResponseEntity<Object>(
				new EmployeePortalException("Internal Error", e.getMessage(), LocalDateTime.now()),
				HttpStatus.BAD_REQUEST);
	}

}
