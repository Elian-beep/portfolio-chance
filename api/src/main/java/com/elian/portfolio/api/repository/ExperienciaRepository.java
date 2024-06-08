package com.elian.portfolio.api.repository;

import com.elian.portfolio.api.entity.Experiencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ExperienciaRepository extends JpaRepository<Experiencia, UUID> {
}
