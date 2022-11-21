package com.petrsushilin.ifmo.payonway.repository;

import com.petrsushilin.ifmo.payonway.entity.Cafe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CafeRepository extends JpaRepository<Cafe, Long> {

}
