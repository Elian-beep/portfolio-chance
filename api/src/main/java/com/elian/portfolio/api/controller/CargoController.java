package com.elian.portfolio.api.controller;

import com.elian.portfolio.api.dto.CargoDTO;
import com.elian.portfolio.api.dto.ExperienciaDTO;
import com.elian.portfolio.api.entity.Cargo;
import com.elian.portfolio.api.service.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/cargo")
public class CargoController {
    @Autowired
    CargoService cargoService;

    @GetMapping
    public ResponseEntity<Set<CargoDTO>> pegarCargos(){
        System.out.println("Buscando cargos...");
        try {
            Set<CargoDTO> cargos = cargoService.getCargos();
            return ResponseEntity.ok().body(cargos);
        }catch (Exception e){
            System.out.println("Erro ao buscar cargos");
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<CargoDTO> cadastrarCargo(@RequestBody @Validated CargoDTO newCargo) {
        try{
            Cargo cargo = cargoService.insertCargo(newCargo);
            return ResponseEntity.created(null).body(cargo.toDto());
        }catch (Exception e){
            System.out.println("Erro ao inserir cargo: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CargoDTO> alterarCargo(@RequestBody CargoDTO cargoDTO, @PathVariable UUID id){
        try{
            CargoDTO cargoAtualizado = cargoService.updateCargo(id, cargoDTO);
            return ResponseEntity.ok().body(cargoAtualizado);
        }catch (Exception e){
            System.out.println("Erro ao atualizar cargo: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarCargo(@PathVariable UUID id){
        try{
            cargoService.deleteCargo(id);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            System.out.println("Erro ao excluir cargo: " + e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
