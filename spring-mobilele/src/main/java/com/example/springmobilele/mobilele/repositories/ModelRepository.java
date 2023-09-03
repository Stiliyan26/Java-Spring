package com.example.springmobilele.mobilele.repositories;

import com.example.springmobilele.mobilele.domain.entities.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepository extends JpaRepository<Model, String> {

}
