package com.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.auth.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("from User as u where u.username=:username")
	Optional<User> findByUsername(@Param("username") String username);
	
}
