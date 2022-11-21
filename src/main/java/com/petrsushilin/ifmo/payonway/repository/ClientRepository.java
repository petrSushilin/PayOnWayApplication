package com.petrsushilin.ifmo.payonway.repository;

import com.petrsushilin.ifmo.payonway.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query("SELECT c FROM Client c WHERE c.login = ?1")
    Optional<Client> findClientByLogin(String login);
}
