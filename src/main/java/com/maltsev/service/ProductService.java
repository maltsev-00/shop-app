package com.maltsev.service;


import com.maltsev.model.ProductShortInfo;
import com.maltsev.model.dto.ProductDto;
import com.maltsev.model.request.ProductRequest;

import java.util.List;

public interface ProductService {

    List<ProductDto> getProducts();

    ProductShortInfo saveProduct(ProductRequest productRequest);
}
