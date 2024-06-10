package com.elian.portfolio.api.dto;

import java.util.Set;
import java.util.UUID;

public record PersonaWithIdDTO(UUID id, String nome, String curriculo, Set<LinkWithIdDTO> linksDto) {
}
