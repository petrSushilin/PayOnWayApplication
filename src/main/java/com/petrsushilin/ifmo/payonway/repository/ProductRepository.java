package com.petrsushilin.ifmo.payonway.repository;

import com.petrsushilin.ifmo.payonway.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
