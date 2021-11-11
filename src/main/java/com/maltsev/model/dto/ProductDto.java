package com.maltsev.model.dto;

import com.maltsev.model.entity.CategoryProductEntity;
import lombok.Builder;
import lombok.Value;

import java.util.List;
import java.util.Set;
import java.util.UUID;


@Value
@Builder
public class ProductDto {
    UUID id;
    String name;
    String description;
    int price;
    List<ManufacturerProductDto> manufacturerProduct;
    ProductPhotoDto productPhoto;
    List<CategoryProductEntity> categoryProducts;
}
