package com.persistent.employeeportal.repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.persistent.employeeportal.entity.TodoDetails;

@Repository
public interface TodoRepository extends CrudRepository<TodoDetails, Long> {

	public List<TodoDetails> findByCreatedDateAndEmployeeId(Date date, long employeeId);

	List<TodoDetails> findAllByEmployeeId(long id);

	Optional<TodoDetails> findByTodoIdAndEmployeeId(long todoId, long EmpId);

}
