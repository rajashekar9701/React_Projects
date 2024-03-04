package com.persistent.employeeportal.service.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.persistent.employeeportal.entity.TodoDetails;
import com.persistent.employeeportal.repository.PersonalInformationRepository;
import com.persistent.employeeportal.repository.TodoRepository;


@Service
public class TodoServiceImpl {

	@Autowired
	TodoRepository todoRepository;
	
	@Autowired
	PersonalInformationRepository persRepository;
	

//	@Autowired
//	EmployeeDetailsRepository employeeDetailsRepository;

	public TodoDetails saveTodo(TodoDetails todoDetails, String username) throws Exception {

		if (todoDetails.getCreatedDate().before(Date.valueOf(LocalDate.now())))
			throw new Exception("Task creation date should be present or future date");

		todoDetails.setEmployeeId(getEmployeeId(username));
		return todoRepository.save(todoDetails);
	}

	public long getEmployeeId(String emailId) {
		 return persRepository.findEmployeeIdByEmployeeEmailId(emailId);
	}

	public List<TodoDetails> findByCreatedDateAndEmployeeId(String createdDate, long employeeId) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date = LocalDate.parse(createdDate, format);
		System.out.println("#####"+Date.valueOf(date));
		return todoRepository.findByCreatedDateAndEmployeeId(Date.valueOf(date), employeeId);
	}

	public List<TodoDetails> findAllByEmployeeId(long employeeId) {
		return todoRepository.findAllByEmployeeId(employeeId);
	}

	public TodoDetails updateTodos(TodoDetails todo, String username) throws Exception {
		Optional<TodoDetails> todoDetails = todoRepository.findByTodoIdAndEmployeeId(todo.getTodoId(),
				getEmployeeId(username));
		if (todoDetails.isPresent()) {
			todoDetails.get().setCompleted(todo.isCompleted());
			todoDetails.get().setStartTime(todo.getStartTime());
			todoDetails.get().setTodoName(todo.getTodoName());

			return todoRepository.save(todoDetails.get());
		} else {
			throw new Exception("Todo item not found.");
		}
	}

	public void deleteTodoItem(long todoId, String username) throws Exception {
		Optional<TodoDetails> todoDetails = todoRepository.findByTodoIdAndEmployeeId(todoId, getEmployeeId(username));
		if (todoDetails.isPresent()) {
			todoRepository.deleteById(todoId);
		} else {
			throw new Exception("Wrong todo id for the employee");
		}

	}
}
