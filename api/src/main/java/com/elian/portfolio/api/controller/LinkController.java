package com.elian.portfolio.api.controller;

import com.elian.portfolio.api.dto.LinkDTO;
import com.elian.portfolio.api.entity.Link;
import com.elian.portfolio.api.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/link")
public class LinkController {
    @Autowired
    LinkService linkService;

    @GetMapping
    public ResponseEntity<List<LinkDTO>> pegarLinks(){
        System.out.println("Buscando links...");
        try{
            List<LinkDTO> links = linkService.getLinks();
            return ResponseEntity.ok().body(links);
        }catch (Exception e){
            System.out.println("Erro ao buscar links: " + e.getMessage());
            return  ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<LinkDTO> cadastrarLink(@RequestBody @Validated LinkDTO linkDTO){
        try{
            Link link = linkService.insertLink(linkService.convertEntity(linkDTO));
            return ResponseEntity.created(null).body(link.toDto());
        }catch (Exception e){
            System.out.println("Erro ao inserir link: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<LinkDTO> alterarLink(@RequestBody LinkDTO linkDTO, @PathVariable UUID id){
        try{
            LinkDTO linkAtualizado = linkService.updateLink(id, linkDTO);
            return ResponseEntity.ok().body(linkAtualizado);
        } catch (Exception e) {
            System.out.println("Erro ao atualizar link: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarLink(@PathVariable UUID id){
        try{
            linkService.deleteLink(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }


}
