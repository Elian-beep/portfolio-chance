package com.elian.portfolio.api.controller;

import com.elian.portfolio.api.entity.Link;
import com.elian.portfolio.api.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/link")
public class LinkController {
    @Autowired
    LinkService linkService;

    @GetMapping
    public ResponseEntity<List<Link>> pegarLinks(){
        System.out.println("Buscando links...");
        try{
            List<Link> links = linkService.getLinks();
            return ResponseEntity.ok().body(links);
        }catch (Exception e){
            System.out.println("Erro ao buscar links: " + e.getMessage());
            return  ResponseEntity.badRequest().build();
        }
    }
}
