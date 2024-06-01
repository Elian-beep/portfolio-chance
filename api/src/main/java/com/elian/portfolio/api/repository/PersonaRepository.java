package com.elian.portfolio.api.repository;

import com.elian.portfolio.api.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PersonaRepository extends JpaRepository<Persona, UUID> {
}
