package com.elian.portfolio.api.controller;

import com.elian.portfolio.api.entity.Aluno;
import com.elian.portfolio.api.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/aluno")
public class AlunoController {
    @Autowired
    AlunoRepository alunoRepository;

    @GetMapping
    public List<Aluno> list(){
        System.out.println("Recuperando alunos...");
        return alunoRepository.findAll();
    }

    @PostMapping
    public Aluno salvar (@RequestBody Aluno aluno){
        aluno.setId(UUID.randomUUID());
        System.out.println("Salvando aluno");
        alunoRepository.save(aluno);
        return aluno;
    }


}
