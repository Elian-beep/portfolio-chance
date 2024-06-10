package com.elian.portfolio.api.service;

import com.elian.portfolio.api.dto.LinkDTO;
import com.elian.portfolio.api.dto.LinkWithIdDTO;
import com.elian.portfolio.api.entity.Link;
import com.elian.portfolio.api.repository.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Consumer;

@Service
public class LinkService {
    @Autowired
    LinkRepository linkRepository;
    @Autowired
    PersonaService personaService;

    public Set<LinkWithIdDTO> getLinks(){
        Set<LinkWithIdDTO> linksWithIdDto = new LinkedHashSet<>();
        linkRepository.findAll().forEach(link -> linksWithIdDto.add(link.toWithIdDTO()));
        return linksWithIdDto;
    }

    public Link insertLink(Link link){
        if(link.getId() == null){
            link.setId(UUID.randomUUID());
        }
        link.setPersona(personaService.getPersona());
        linkRepository.save(link);
        return linkRepository.findById(link.getId()).get();
    }

    public LinkDTO updateLink(UUID id, LinkDTO newLink) throws Exception {
        Optional<Link> ofcLink = findById(id);
        if(ofcLink.isPresent()){
            Link link = ofcLink.get();
            updateIfNotNull(link::setLink, newLink.link());
            updateIfNotNull(link::setIconUrl, newLink.iconUrl());
            updateIfNotNull(link::setTitulo, newLink.titulo());

            /* LinkMapper.INSTACE.updateLinkFromDto(newLink, link);
            linkRepository.save(link); */

            /* link.setTitulo(newLink.link() != null ? newLink.link() : link.getTitulo());
            link.setLink(newLink.link() != null ? newLink.link() : link.getLink());
            link.setIconUrl(newLink.iconUrl() != null ? newLink.iconUrl() : link.getIconUrl());
            System.out.println("TITULO =====> " + newLink.titulo());
            linkRepository.save(link);*/

            linkRepository.save(link);
            return link.toDto();
        }
        throw new Exception();
    }

    private <T> void updateIfNotNull(Consumer<T> setter, T value) {
        if (value != null) {
            setter.accept(value);
        }
    }

    public void deleteLink(UUID id) throws Exception{
        Optional<Link> ofcLink = findById(id);
        if(!ofcLink.isPresent()){
            throw new Exception();
        }
        linkRepository.deleteById(id);
    }

    public Link convertEntity(LinkDTO linkDTO){
        Link link = new Link(linkDTO.link(), linkDTO.titulo(), linkDTO.iconUrl());
        return link;
    }

    public Optional<Link> findById(UUID id){
        return  linkRepository.findById(id);
    }
}
