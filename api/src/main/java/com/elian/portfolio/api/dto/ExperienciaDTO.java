package com.elian.portfolio.api.dto;

import java.util.Set;

public record ExperienciaDTO(String empresa, String inicio, String termino, String descricao, Set<CargoDTO> cargosDto) {
}
