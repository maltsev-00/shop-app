package com.maltsev.model.dto;

import com.maltsev.model.entity.CategoryProductEntity;
import lombok.Builder;
import lombok.Value;

import java.util.Set;
import java.util.UUID;


@Value
@Builder
public class ProductDto {
    UUID id;
    String name;
    String description;
    int price;
    Set<ManufacturerProductDto> manufacturerProduct;
    ProductPhotoDto productPhoto;
    Set<CategoryProductEntity> categoryProducts;
}
