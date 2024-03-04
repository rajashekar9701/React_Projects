package com.persistent.employeeportal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.persistent.employeeportal.entity.TodoDetails;
import com.persistent.employeeportal.repository.PersonalInformationRepository;
import com.persistent.employeeportal.repository.TodoRepository;
import com.persistent.employeeportal.service.impl.TodoServiceImpl;

@SpringBootTest
public class TodoServiceImplTest {

	@Autowired
	TodoServiceImpl todoServiceImpl;

	@MockBean
	TodoRepository todoRepository;

	@MockBean
	PersonalInformationRepository personInfoRepository;

	private TodoDetails todoDetails;

	@BeforeEach
	public void initializeTodo() {

		TodoDetails todoDetails = new TodoDetails();
		todoDetails.setTodoId(1L);
		todoDetails.setTodoName("Task");

		DateTimeFormatter format = DateTimeFormatter.ofPattern("hh:mm a");

		todoDetails.setStartTime(LocalTime.parse("11:50 PM", format));
		todoDetails.setCreatedDate(Date.valueOf("2023-06-12"));
		todoDetails.setCompleted(false);
		todoDetails.setEmployeeId(2L);

		this.todoDetails = todoDetails;
	}

	@Test
	public void testSaveTodo() throws Exception {

		when(todoRepository.save(todoDetails)).thenReturn(todoDetails);
		when(personInfoRepository.findEmployeeIdByEmail("abc@xyz.com")).thenReturn(2L);
		
		TodoDetails todo = todoServiceImpl.saveTodo(todoDetails, "abc@xyz.com");
		assertEquals(2, todo.getEmployeeId());
		
		// Negative case
		
		TodoDetails todoDetails1 = new TodoDetails();
		todoDetails1.setTodoId(1L);
		todoDetails1.setTodoName("Task");

		DateTimeFormatter format = DateTimeFormatter.ofPattern("hh:mm a");

		todoDetails1.setStartTime(LocalTime.parse("11:50 PM", format));
		todoDetails1.setCreatedDate(Date.valueOf("2023-05-01"));
		todoDetails1.setCompleted(false);
		todoDetails1.setEmployeeId(3);
		
		Exception ex = assertThrows(Exception.class, () -> todoServiceImpl.saveTodo(todoDetails1, "abc@xyz.com"));
		assertTrue(ex.getMessage().contains("Task creation date should be present or future date"));
	}

	@Test
	public void findByCreatedDateAndEmployeeIdTest() {

		List<TodoDetails> todoList = new ArrayList<>();
		todoList.add(todoDetails);
		when(todoRepository.findByCreatedDateAndEmployeeId(Date.valueOf("2023-05-11"), 2)).thenReturn(todoList);
		assertEquals(1, todoServiceImpl.findByCreatedDateAndEmployeeId("2023-05-11", 2).size());
		
		// Negative Scenario
		
		assertThrows(DateTimeParseException.class, () -> todoServiceImpl.findByCreatedDateAndEmployeeId("20230511", 2));
		
	}

	
	@Test
	public void testFindAllByEmployeeId() {

		List<TodoDetails> todoList = new ArrayList<>();
		todoList.add(todoDetails);
		when(todoRepository.findAllByEmployeeId(todoDetails.getEmployeeId())).thenReturn(todoList);
		assertEquals(1, todoServiceImpl.findAllByEmployeeId(2).size());
	}

	@Test
	public void testUpdateTodo() throws Exception {

		when(todoRepository.findByTodoIdAndEmployeeId(todoDetails.getTodoId(), todoDetails.getEmployeeId()))
				.thenReturn(Optional.of(todoDetails));

		when(todoRepository.save(todoDetails)).thenReturn(todoDetails);
		when(personInfoRepository.findEmployeeIdByEmail("abc@xyz.com")).thenReturn(2L);

		assertEquals(2L, todoServiceImpl.updateTodos(todoDetails, "abc@xyz.com").getEmployeeId());
		
		// Negetive scenario
		
		when(todoRepository.findByTodoIdAndEmployeeId(todoDetails.getTodoId(), todoDetails.getEmployeeId()))
		.thenReturn(null);
		
		TodoDetails todoDetails1 = new TodoDetails();
		todoDetails1.setTodoId(3);
		todoDetails1.setTodoName("Task");

		DateTimeFormatter format = DateTimeFormatter.ofPattern("hh:mm a");

		todoDetails1.setStartTime(LocalTime.parse("11:50 PM", format));
		todoDetails1.setCreatedDate(Date.valueOf("2023-05-12"));
		todoDetails1.setCompleted(false);
		todoDetails1.setEmployeeId(2);
		
		Exception ex = assertThrows(Exception.class, () -> todoServiceImpl.updateTodos(todoDetails1, "abc@xyz.com"));
		assertTrue(ex.getMessage().contains("Todo item not found."));
	}

	@Test
	public void testDeleteTodo() throws Exception {

		when(todoRepository.findByTodoIdAndEmployeeId(todoDetails.getTodoId(), 2L))
				.thenReturn(Optional.of(todoDetails));
		when(personInfoRepository.findEmployeeIdByEmail("abc@xyz.com")).thenReturn(2L);
		
		todoServiceImpl.deleteTodoItem(todoDetails.getTodoId(), "abc@xyz.com");
		verify(todoRepository, times(1)).deleteById(todoDetails.getTodoId());
		
		// Negative scenario
		
		when(todoRepository.findByTodoIdAndEmployeeId(todoDetails.getTodoId(), todoDetails.getEmployeeId()))
		.thenReturn(null);
				
		Exception ex = assertThrows(Exception.class, () -> todoServiceImpl.deleteTodoItem(3, "abc@xyz.com"));
		assertTrue(ex.getMessage().contains("Wrong todo id for the employee"));
		
	}

}
