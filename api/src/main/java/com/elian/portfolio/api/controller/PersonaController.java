package com.elian.portfolio.api.controller;

import com.elian.portfolio.api.dto.PersonaDTO;
import com.elian.portfolio.api.entity.Persona;
import com.elian.portfolio.api.repository.PersonaRepository;
import com.elian.portfolio.api.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/persona")
public class PersonaController {
    @Autowired
    PersonaService personaService;
    @Autowired
    PersonaRepository personaRepository;

    @GetMapping
    public ResponseEntity<PersonaDTO> pegarPersona(){
        System.out.println("Pegando informações da persona");
        try{
            Persona persona = personaService.getPersona();
            return ResponseEntity.ok().body(persona.toDTO());
        }catch (Exception e){
            System.out.println("Erro ao buscar dados da persona: "+ e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity<PersonaDTO> alterarPersona(@RequestBody PersonaDTO personaDTO){
        try{
            Persona persona = personaService.convertEntity(personaDTO);
            personaService.updatePersona(persona);
            return ResponseEntity.ok().body(personaDTO);
        }catch (Exception e){
            System.out.println("Erro ao atualizar persona: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

}
