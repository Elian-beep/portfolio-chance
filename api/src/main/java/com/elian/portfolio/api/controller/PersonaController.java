package com.elian.portfolio.api.controller;

import com.elian.portfolio.api.dto.PersonaDTO;
import com.elian.portfolio.api.dto.PersonaWithIdDTO;
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

    @GetMapping
    public ResponseEntity<PersonaWithIdDTO> pegarPersona(){
        System.out.println("Pegando informações da persona");
        try{
            Persona persona = personaService.getPersona();
            return ResponseEntity.ok().body(persona.toWithIdDTO());
        }catch (Exception e){
            System.out.println("Erro ao buscar dados da persona: "+ e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity<PersonaDTO> alterarPersona(@RequestBody PersonaDTO personaDTO){
        try{
            PersonaDTO updatedPersona = personaService.updatePersona(personaDTO);
            return ResponseEntity.ok().body(updatedPersona);
        }catch (Exception e){
            System.out.println("Erro ao atualizar persona: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

}
