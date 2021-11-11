package com.maltsev.model.response;

import com.maltsev.model.dto.CategoryProductDto;
import com.maltsev.model.dto.ManufacturerProductDto;
import com.maltsev.model.dto.ProductPhotoDto;
import lombok.Builder;
import lombok.Value;

import java.util.List;
import java.util.UUID;

@Value
@Builder
public class ProductResponse {
    UUID id;
    String name;
    String description;
    int price;
    List<ManufacturerProductDto> manufacturerProduct;
    ProductPhotoDto productPhoto;
    List<CategoryProductDto> categoryProducts;
}
