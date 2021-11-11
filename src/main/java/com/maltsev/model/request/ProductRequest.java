package com.maltsev.model.request;

import lombok.Value;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Value
public class ProductRequest {
    @NotNull
    UUID id;
    @NotNull
    @NotEmpty
    String name;
    @NotNull
    String description;
    @NotNull
    int price;
    @NotNull
    List<ManufacturerProductRequest> manufacturerProduct;
    @NotNull
    ProductPhotoRequest productPhoto;
    @NotNull
    List<CategoryProductRequest> categoryProducts;
}
