package com.elian.portfolio.api.entity;

import com.elian.portfolio.api.dto.CargoDTO;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "cargo")
public class Cargo {
    @Id
    @Column(name = "id")
    private UUID id = UUID.randomUUID();
    @Column(name = "titulo")
    private String titulo;
    @ManyToOne
    @JoinColumn(name = "id_experiencia", referencedColumnName = "id")
    private Experiencia experiencia;

    public Cargo(UUID id, String titulo, Experiencia experiencia) {
        this.id = id;
        this.titulo = titulo;
        this.experiencia = experiencia;
    }

    public Cargo(String titulo, Experiencia experiencia) {
        this.titulo = titulo;
        this.experiencia = experiencia;
    }

    public Cargo(){}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Experiencia getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(Experiencia experiencia) {
        this.experiencia = experiencia;
    }

    public CargoDTO toDto(){
        CargoDTO cargoDTO = new CargoDTO(this.titulo, this.experiencia.getId());
        return cargoDTO;
    }
}
