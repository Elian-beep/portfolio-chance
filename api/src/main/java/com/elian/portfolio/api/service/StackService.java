package com.elian.portfolio.api.service;

import com.elian.portfolio.api.dto.StackDTO;
import com.elian.portfolio.api.entity.Stack;
import com.elian.portfolio.api.repository.StackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StackService {
    @Autowired
    StackRepository stackRepository;

    public Set<Stack> getStacks(){
        Set<Stack> stacks = new LinkedHashSet<>();
        stackRepository.findAll().forEach(s -> stacks.add(s));
        return stacks;
    }

    public StackDTO insertStack(StackDTO stackDTO) throws Exception {
        Optional<Stack> existsStack = stackRepository.findByTitulo(stackDTO.titulo());
        if(existsStack.isPresent()){
            throw new Exception();
        }
        stackRepository.save(convertEntity(stackDTO));
        return stackRepository.findByTitulo(stackDTO.titulo()).get().toDto();
    }

    public void deleteStack(UUID id) throws Exception{
        Optional<Stack> ofcStack = stackRepository.findById(id);
        if(!ofcStack.isPresent()){
            throw new Exception();
        }
        stackRepository.deleteById(id);
    }

    public Stack convertEntity (StackDTO stackDTO){
        return new Stack(stackDTO.titulo(), stackDTO.iconUrl());
    }
}
