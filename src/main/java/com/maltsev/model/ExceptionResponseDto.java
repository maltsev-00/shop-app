package com.maltsev.model;

import lombok.Builder;
import lombok.Value;

import java.time.Instant;

@Value
@Builder
public class ExceptionResponseDto {
    Instant timestamp;
    String message;
}
