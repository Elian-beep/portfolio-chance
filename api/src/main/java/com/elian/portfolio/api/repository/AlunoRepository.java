package com.elian.portfolio.api.repository;

import com.elian.portfolio.api.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AlunoRepository extends JpaRepository<Aluno, UUID> {
}
