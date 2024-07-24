package com.auth.repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.auth.entities.ERole;
import com.auth.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	Optional<Role> findByName(ERole name);
	
}
