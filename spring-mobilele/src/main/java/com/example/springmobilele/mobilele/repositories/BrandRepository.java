package com.example.springmobilele.mobilele.repositories;

import com.example.springmobilele.mobilele.domain.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, String> {

}

