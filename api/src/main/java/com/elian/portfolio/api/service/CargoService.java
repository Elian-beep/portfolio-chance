package com.elian.portfolio.api.service;

import com.elian.portfolio.api.dto.CargoDTO;
import com.elian.portfolio.api.dto.CargoWithIdDTO;
import com.elian.portfolio.api.dto.ExperienciaDTO;
import com.elian.portfolio.api.entity.Cargo;
import com.elian.portfolio.api.entity.Experiencia;
import com.elian.portfolio.api.repository.CargoRepository;
import com.elian.portfolio.api.repository.ExperienciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;

@Service
public class CargoService {
    @Autowired
    CargoRepository cargoRepository;
    @Autowired
    ExperienciaRepository experienciaRepository;

    public Cargo insertCargo(CargoDTO cargoDTO) throws Exception {
        Optional<Experiencia> experiencia = findExperiencia(cargoDTO.id_experiencia());
        if(!experiencia.isPresent()){
            throw new Exception();
        }
        Cargo cargo = convertEntity(cargoDTO);
        cargoRepository.save(cargo);
        return cargo;
    }

    public CargoDTO updateCargo(UUID id, CargoDTO newCargo) throws Exception{
        Optional<Cargo> ofcCargo = findById(id);
        System.out.println(ofcCargo.get().getId());
        if(ofcCargo.isPresent()){
            Cargo cargo = ofcCargo.get();
            updateIfNotNull(cargo::setTitulo, newCargo.titulo());

            UUID id_xp = newCargo.id_experiencia() != null ? newCargo.id_experiencia() : cargo.getExperiencia().getId();
            Experiencia experiencia = findExperiencia(id_xp).get();
            updateIfNotNull(cargo::setExperiencia, experiencia);

            cargoRepository.save(cargo);
            return cargo.toDto();
        }
        throw new Exception();
    }

    public void deleteCargo(UUID id) throws Exception{
        Optional<Cargo> ofcCargo = findById(id);
        if(!ofcCargo.isPresent()){
            throw new Exception();
        }
        cargoRepository.deleteById(id);
    }

    private <T> void updateIfNotNull(Consumer<T> setter, T value) {
        if (value != null) {
            setter.accept(value);
        }
    }

    public Cargo convertEntity(CargoDTO cargoDTO){
        Optional<Experiencia> experiencia = experienciaRepository.findById(cargoDTO.id_experiencia());
        Cargo cargo = new Cargo(cargoDTO.titulo(), experiencia.get());
        return cargo;
    }

    public Optional<Cargo> findById(UUID id){
        return cargoRepository.findById(id);
    }

    public Optional<Experiencia> findExperiencia(UUID id) throws Exception {
        Optional<Experiencia> experiencia = experienciaRepository.findById(id);
        if(experiencia.isPresent()){
            return experiencia;
        }
        throw new Exception();
    }
}
