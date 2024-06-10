package com.elian.portfolio.api.service;

import com.elian.portfolio.api.dto.PersonaDTO;
import com.elian.portfolio.api.entity.Persona;
import com.elian.portfolio.api.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PersonaService {
    @Autowired
    PersonaRepository personaRepository;

    public Persona getPersona(){
        if(personaRepository.findAll().getFirst() != null){
            return personaRepository.findAll().getFirst();
        }
        Persona persona = new Persona(UUID.randomUUID(), "Elian Batista", "https://docs.google.com/document/d/1d-yDdWXQ7X046Yg-WcVZc9t2gtauQTFAqh54hQ6NiAs/edit?usp=sharing");
        personaRepository.save(persona);
        return persona;
    }

    public PersonaDTO updatePersona(PersonaDTO newPersona){
        Persona oldPersona = getPersona();
        oldPersona.setNome(newPersona.nome() != null ? newPersona.nome() : oldPersona.getNome());
        oldPersona.setCurriculo(newPersona.curriculo() != null ? newPersona.curriculo() : oldPersona.getCurriculo());
        personaRepository.save(oldPersona);
        return oldPersona.toDTO();
    }

    public Persona convertEntity(PersonaDTO personaDTO){
        Persona persona = new Persona();
        persona.setNome(personaDTO.nome());
        persona.setCurriculo(personaDTO.curriculo());
        return persona;
    }
}
