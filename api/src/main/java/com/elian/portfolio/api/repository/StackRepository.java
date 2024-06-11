package com.elian.portfolio.api.repository;

import com.elian.portfolio.api.entity.Stack;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface StackRepository extends JpaRepository<Stack, UUID> {
    public Optional<Stack> findByTitulo(String titulo);
}
