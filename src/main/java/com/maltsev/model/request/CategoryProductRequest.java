package com.maltsev.model.request;


import lombok.Value;

import java.util.UUID;

@Value
public class CategoryProductRequest {
    UUID id;
    String name;
}
