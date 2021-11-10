package com.maltsev.model.dto;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class ProductPhotoDto {
    UUID id;
    String url;
}
