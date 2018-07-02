package com.sergio.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sergio.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

	Optional<Product> findById(Long id);
}