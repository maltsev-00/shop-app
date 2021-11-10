package com.maltsev.model.response;

import com.maltsev.model.dto.CategoryProductDto;
import com.maltsev.model.dto.ManufacturerProductDto;
import com.maltsev.model.dto.ProductPhotoDto;
import lombok.Builder;
import lombok.Value;

import java.util.Set;
import java.util.UUID;

@Value
@Builder
public class ProductResponse {
    UUID id;
    String name;
    String description;
    int price;
    Set<ManufacturerProductDto> manufacturerProduct;
    ProductPhotoDto productPhoto;
    Set<CategoryProductDto> categoryProducts;
}
