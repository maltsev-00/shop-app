package com.maltsev.model.request;

import lombok.Value;

import java.util.UUID;

@Value
public class ProductPhotoRequest {
    UUID id;
    String url;
}
