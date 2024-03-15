package com.epam.qms.dto;

import java.time.LocalDateTime;

import lombok.Builder;

@Builder
public record ExceptionDTO(String message, LocalDateTime timestamp) {

}
