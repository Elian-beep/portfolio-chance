package com.elian.portfolio.api.service;

import com.elian.portfolio.api.dto.ExperienciaDTO;
import com.elian.portfolio.api.entity.Experiencia;
import com.elian.portfolio.api.repository.ExperienciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Consumer;

@Service
public class ExperienciaService {
    @Autowired
    ExperienciaRepository experienciaRepository;

    public Set<Experiencia> getExperiencias(){
        return new LinkedHashSet<>(experienciaRepository.findAll());
    }

    public ExperienciaDTO insertExperiencia(Experiencia experiencia){
        if(experiencia.getId() == null){
            experiencia.setId(UUID.randomUUID());
        }
        experienciaRepository.save(experiencia);
        return experienciaRepository.findById(experiencia.getId()).get().toDto();
    }

    public ExperienciaDTO updateExperiencia(UUID id, ExperienciaDTO newExperiencia) throws Exception {
        Optional<Experiencia> ofcExperiencia = findById(id);
        if(ofcExperiencia.isPresent()){
            Experiencia experiencia = ofcExperiencia.get();
            updateIfNotNull(experiencia::setEmpresa, newExperiencia.descricao());
            updateIfNotNull(experiencia::setInicio, newExperiencia.inicio());
            updateIfNotNull(experiencia::setTermino, newExperiencia.termino());
            updateIfNotNull(experiencia::setDescricao, newExperiencia.descricao());

            experienciaRepository.save(experiencia);
            return experiencia.toDto();
        }
        throw new Exception();
    }

    public void deleteExperiencia(UUID id) throws Exception{
        Optional<Experiencia> ofcExperiecia = findById(id);
        if(!ofcExperiecia.isPresent()){
            throw new Exception();
        }
        experienciaRepository.deleteById(id);
    }

    public Experiencia convertEntity(ExperienciaDTO experienciaDTO){
        Experiencia experiencia = new Experiencia(experienciaDTO.empresa(), experienciaDTO.inicio(), experienciaDTO.termino(), experienciaDTO.descricao());
        return experiencia;
    }

    public Optional<Experiencia> findById(UUID id) { return experienciaRepository.findById(id); }

    private <T> void updateIfNotNull(Consumer<T> setter, T value) {
        if (value != null) {
            setter.accept(value);
        }
    }
}
