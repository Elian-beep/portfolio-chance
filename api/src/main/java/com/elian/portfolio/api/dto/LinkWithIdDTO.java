package com.elian.portfolio.api.dto;

import java.util.UUID;

public record LinkWithIdDTO(UUID id, String titulo, String link, String iconUrl) {
}
