package com.elian.portfolio.api.controller;

import com.elian.portfolio.api.dto.ExperienciaDTO;
import com.elian.portfolio.api.dto.ExperienciaWithDTO;
import com.elian.portfolio.api.entity.Experiencia;
import com.elian.portfolio.api.service.ExperienciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/experiencia")
public class ExperienciaController {
    @Autowired
    ExperienciaService experienciaService;

    @GetMapping
    public ResponseEntity<Set<ExperienciaWithDTO>> pegarExperiencias(){
        try{
            System.out.println("buscando experiências");
            Set<ExperienciaWithDTO> experienciaDTO = experienciaService.getExperiencias();
            return ResponseEntity.ok().body(experienciaDTO);
        }catch (Exception e){
            System.out.println("Erro ao buscar experiências: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<ExperienciaDTO> cadastrarExperiencia(@RequestBody @Validated ExperienciaDTO experienciaDTO){
        try{
            ExperienciaDTO experiencia = experienciaService.insertExperiencia(experienciaService.convertEntity(experienciaDTO));
            return ResponseEntity.created(null).body(experiencia);
        }catch (Exception e){
            System.out.println("Erro ao cadastrar experiencia: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExperienciaDTO> alterarExperiencia(@RequestBody ExperienciaDTO experienciaDTO, @PathVariable UUID id){
        try{
            ExperienciaDTO experieciaAtualizada = experienciaService.updateExperiencia(id, experienciaDTO);
            return ResponseEntity.ok().body(experieciaAtualizada);
        }catch (Exception e){
            System.out.println("Erro ao atualizar experiência: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarExperiencia(@PathVariable UUID id){
        try{
            experienciaService.deleteExperiencia(id);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            System.out.println("Erro ao excluir experiencia: " + e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
