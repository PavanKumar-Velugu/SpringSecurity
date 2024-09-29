package com.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.security.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	@Query(value="from User where username = :username")
	User findByUsername(@Param("username") String username);

}
