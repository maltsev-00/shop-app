package com.maltsev.model;

import com.maltsev.model.dto.CategoryProductDto;
import com.maltsev.model.dto.ManufacturerProductDto;
import com.maltsev.model.dto.ProductPhotoDto;
import com.maltsev.model.entity.CategoryProductEntity;
import com.maltsev.model.entity.ManufacturerProductEntity;
import com.maltsev.model.entity.ProductPhotoEntity;
import com.maltsev.model.request.CategoryProductRequest;
import lombok.Builder;
import lombok.Value;

import java.util.List;
import java.util.Set;

@Value
@Builder
public class ProductShortInfo {
    String name;
    String description;
    int price;
    Set<ManufacturerProductDto> manufacturerProduct;
    ProductPhotoDto productPhoto;
    List<CategoryProductDto> categoryProducts;
}
