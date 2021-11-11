package com.maltsev.service;


import com.maltsev.model.ProductShortInfo;
import com.maltsev.model.dto.ProductDto;
import com.maltsev.model.request.ProductRequest;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    List<ProductDto> getProducts(PageRequest pageRequest);

    ProductShortInfo saveProduct(ProductRequest productRequest);

    void deleteById(UUID id);

    void changeProduct(ProductRequest productRequest);

    List<ProductDto> findProductByPrice(int price);
}
