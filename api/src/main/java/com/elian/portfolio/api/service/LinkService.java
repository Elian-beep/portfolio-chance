package com.elian.portfolio.api.service;

import com.elian.portfolio.api.entity.Link;
import com.elian.portfolio.api.repository.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinkService {
    @Autowired
    LinkRepository linkRepository;

    public List<Link> getLinks(){
        return linkRepository.findAll();
    }
}
