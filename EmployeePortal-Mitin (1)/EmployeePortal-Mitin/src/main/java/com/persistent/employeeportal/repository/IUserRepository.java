package com.persistent.employeeportal.repository;




import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.persistent.employeeportal.entity.User;

public interface IUserRepository extends JpaRepository<User,Integer> {

    Boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
	User save(User user);


}

