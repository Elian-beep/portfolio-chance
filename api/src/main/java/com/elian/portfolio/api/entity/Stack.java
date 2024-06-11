package com.elian.portfolio.api.entity;

import com.elian.portfolio.api.dto.StackDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "Stack")
public class Stack {
    @Id
    @Column(name = "id")
    private UUID id = UUID.randomUUID();
    @Column(name = "titulo")
    private String titulo;
    @Column(name = "icon_url")
    private String iconUrl;

    public Stack(UUID id, String titulo, String iconUrl) {
        this.id = id;
        this.titulo = titulo;
        this.iconUrl = iconUrl;
    }

    public Stack(String titulo, String iconUrl) {
        this.titulo = titulo;
        this.iconUrl = iconUrl;
    }

    public Stack() {}

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

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public StackDTO toDto() {
        return new StackDTO(this.titulo, this.iconUrl);
    }
}
