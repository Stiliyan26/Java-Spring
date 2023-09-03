package com.example.springmobilele.mobilele.repositories;

import com.example.springmobilele.mobilele.domain.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

}
