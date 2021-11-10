package com.maltsev.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    @NotNull
    private UUID id;
    @NotNull
    @NotEmpty
    private String name;
    @NotNull
    private String description;
    @NotNull
    private int price;
    @NotNull
    private Set<ManufacturerProductRequest> manufacturerProduct;
    @NotNull
    private ProductPhotoRequest productPhoto;
    @NotNull
    private List<CategoryProductRequest> categoryProducts;
}
