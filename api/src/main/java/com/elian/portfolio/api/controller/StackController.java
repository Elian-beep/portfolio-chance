package com.elian.portfolio.api.controller;

import com.elian.portfolio.api.dto.StackDTO;
import com.elian.portfolio.api.entity.Stack;
import com.elian.portfolio.api.service.StackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/stack")
public class StackController {
    @Autowired
    StackService stackService;

    @GetMapping
    public ResponseEntity<Set<Stack>> pegarStacks(){
        try{
            Set<Stack> stacks = stackService.getStacks();
            return ResponseEntity.ok().body(stacks);
        }catch (Exception e){
            System.out.println("Erro ao buscar stacks: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<StackDTO> cadastrarStack(@RequestBody @Validated StackDTO stackDTO){
        try {
            StackDTO newStack = stackService.insertStack(stackDTO);
            return ResponseEntity.created(null).body(newStack);
        } catch (Exception e) {
            System.out.println("Erro ao inserir stack: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deletarStack(@PathVariable UUID id){
        try {
            stackService.deleteStack(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            System.out.println("Erro ao apagar stack: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
