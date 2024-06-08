package com.elian.portfolio.api.entity;

import com.elian.portfolio.api.dto.CargoDTO;
import com.elian.portfolio.api.dto.ExperienciaDTO;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "Experiencia")
public class Experiencia {
    @Id
    @Column(name = "id")
    private UUID id = UUID.randomUUID();
    @Column(name = "empresa")
    private String empresa;
    @Column(name = "inicio")
    private String inicio;
    @Column(name = "termino")
    private String termino;
    @Column(name = "descricao")
    private String descricao;
    @OneToMany(mappedBy = "experiencia")
    private Set<Cargo> cargos = new LinkedHashSet<>();

    public Experiencia(UUID id, String empresa, String inicio, String termino, String descricao, Set<Cargo> cargos) {
        this.id = id;
        this.empresa = empresa;
        this.inicio = inicio;
        this.termino = termino;
        this.descricao = descricao;
        this.cargos = cargos;
    }

    public Experiencia(String empresa, String inicio, String termino, String descricao){
        this.empresa = empresa;
        this.inicio = inicio;
        this.termino = termino;
        this.descricao = descricao;
    }

    public Experiencia(){}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getTermino() {
        return termino;
    }

    public void setTermino(String termino) {
        this.termino = termino;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Set<Cargo> getCargos() {
        return cargos;
    }

    public void setCargos(Set<Cargo> cargos) {
        this.cargos = cargos;
    }

    public ExperienciaDTO toDto(){
        Set<CargoDTO> cargoDTOS = new LinkedHashSet<>();
        this.cargos.forEach(cargo -> cargoDTOS.add(cargo.toDto()));
        ExperienciaDTO experienciaDTO = new ExperienciaDTO(this.empresa, this.inicio, this.termino, this.descricao, cargoDTOS);
        return experienciaDTO;
    }
}
