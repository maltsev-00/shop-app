package com.maltsev.model;

import com.maltsev.model.dto.CategoryProductDto;
import com.maltsev.model.dto.ManufacturerProductDto;
import com.maltsev.model.dto.ProductPhotoDto;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class ProductShortInfo {
    String name;
    String description;
    int price;
    List<ManufacturerProductDto> manufacturerProduct;
    ProductPhotoDto productPhoto;
    List<CategoryProductDto> categoryProducts;
}
