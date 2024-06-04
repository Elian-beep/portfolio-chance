package com.elian.portfolio.api.dto;

import com.elian.portfolio.api.entity.Link;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

public record PersonaDTO (String nome, String curriculo, List<LinkDTO> linksDto){
}
