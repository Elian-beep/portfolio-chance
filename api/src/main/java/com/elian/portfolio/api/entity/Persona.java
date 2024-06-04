package com.elian.portfolio.api.entity;

import com.elian.portfolio.api.dto.LinkDTO;
import com.elian.portfolio.api.dto.PersonaDTO;
import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "Persona")
public class Persona {
    @Id
    @Column(name = "id")
    private UUID id = UUID.randomUUID();
    @Column(name = "nome")
    private String nome;
    @Column(name = "curriculo")
    private String curriculo;
    @OneToMany(mappedBy = "persona")
    private Set<Link> links = new LinkedHashSet<>();

    public Persona(UUID id, String nome, String curriculo, Set<Link> links) {
        this.id = id;
        this.nome = nome;
        this.curriculo = curriculo;
        this.links = links;
    }
    public Persona(UUID id, String nome, String curriculo) {
        this.id = id;
        this.nome = nome;
        this.curriculo = curriculo;
    }

    public Persona(){}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCurriculo() {
        return curriculo;
    }

    public void setCurriculo(String curriculo) {
        this.curriculo = curriculo;
    }

    public Set<Link> getLinks() {
        return links;
    }

    public void setLinks(Set<Link> link) {
        this.links = link;
    }
    public PersonaDTO toDTO(){
        List<LinkDTO> linksDto = new ArrayList<>();
        this.links.forEach(link -> linksDto.add(link.toDto()));
        return new PersonaDTO(this.nome, this.curriculo, linksDto);
    }

    @Override
    public String toString() {
        return "Persona{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", curriculo='" + curriculo + '\'' +
                ", links=" + links +
                '}';
    }
}
