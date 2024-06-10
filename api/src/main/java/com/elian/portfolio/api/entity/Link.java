package com.elian.portfolio.api.entity;

import com.elian.portfolio.api.dto.LinkDTO;
import com.elian.portfolio.api.dto.LinkWithIdDTO;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "Link")
public class Link {
    @Id
    @Column(name = "id")
    private UUID id = UUID.randomUUID();
    @Column(name = "link_url")
    private String link;
    @Column(name = "titulo")
    private String titulo;
    @Column(name = "icon_url")
    private String iconUrl;
    @ManyToOne
    @JoinColumn(name = "id_persona", referencedColumnName = "id")
    private Persona persona;

    public Link(UUID id, String titulo, String link, String iconUrl, Persona persona) {
        this.id = id;
        this.titulo = titulo;
        this.link = link;
        this.iconUrl = iconUrl;
        this.persona = persona;
    }

    public Link(String link, String titulo, String iconUrl) {
        this.link = link;
        this.titulo = titulo;
        this.iconUrl = iconUrl;
    }

    public Link(){}

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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public LinkDTO toDto(){
        return new LinkDTO(this.titulo, this.link, this.iconUrl);
    }

    public LinkWithIdDTO toWithIdDTO(){
        return new LinkWithIdDTO(this.id, this.titulo, this.link, this.iconUrl);
    }

    @Override
    public String toString() {
        return "Link{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", link='" + link + '\'' +
                ", iconUrl='" + iconUrl + '\'' +
                ", persona=" + persona +
                '}';
    }
}
