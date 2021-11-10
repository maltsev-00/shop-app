package com.maltsev.model.request;

import lombok.Value;

import java.util.UUID;

@Value
public class ManufacturerProductRequest {
    UUID id;
    String name;
}
