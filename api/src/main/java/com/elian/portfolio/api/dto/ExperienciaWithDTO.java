package com.elian.portfolio.api.dto;

import java.util.Set;
import java.util.UUID;

public record ExperienciaWithDTO(UUID id, String empresa, String inicio, String termino, String descricao, Set<CargoWithIdDTO> cargosDto) {
}
