package com.persistent.employeeportal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.persistent.employeeportal.entity.TodoDetails;
import com.persistent.employeeportal.repository.TodoRepository;
import com.persistent.employeeportal.service.impl.TodoServiceImpl;

@SpringBootTest
public class TodoServiceImplTest {

	@Autowired
	TodoServiceImpl todoServiceImpl;

	@MockBean
	TodoRepository todoRepository;

	private TodoDetails todoDetails;

	@BeforeEach
	public void initializeTodo() {

		TodoDetails todoDetails = new TodoDetails();
		todoDetails.setTodoId(1L);
		todoDetails.setTodoName("Task");

		DateTimeFormatter format = DateTimeFormatter.ofPattern("hh:mm a");

		todoDetails.setStartTime(LocalTime.parse("11:50 PM", format));
		todoDetails.setCreatedDate(Date.valueOf("2023-05-12"));
		todoDetails.setCompleted(false);
		todoDetails.setEmployeeId(2L);

		this.todoDetails = todoDetails;
	}

	@Test
	public void testSaveTodo() throws Exception {

		when(todoRepository.save(todoDetails)).thenReturn(todoDetails);
		TodoDetails todo = todoServiceImpl.saveTodo(todoDetails);
		assertEquals(2L, todo.getEmployeeId());
	}

	@Test
	public void findByCreatedDateAndEmployeeIdTest() {

		List<TodoDetails> todoList = new ArrayList<>();
		todoList.add(todoDetails);
		when(todoRepository.findByCreatedDateAndEmployeeId(Date.valueOf("2023-05-11"), 2)).thenReturn(todoList);
		assertEquals(1, todoServiceImpl.findByCreatedDateAndEmployeeId("2023-05-11", 2).size());

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

		assertEquals(2L, todoServiceImpl.updateTodos(todoDetails).getEmployeeId());
	}

	@Test
	public void testDeleteTodo() throws Exception {

		when(todoRepository.findByTodoIdAndEmployeeId(todoDetails.getTodoId(), 2L))
				.thenReturn(Optional.of(todoDetails));
		todoServiceImpl.deleteTodoItem(todoDetails.getTodoId());
		verify(todoRepository, times(1)).deleteById(todoDetails.getTodoId());

	}

}
