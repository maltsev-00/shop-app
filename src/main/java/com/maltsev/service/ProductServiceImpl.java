package com.maltsev.service;

import com.maltsev.component.converter.ProductConverter;
import com.maltsev.model.ProductShortInfo;
import com.maltsev.model.dto.ProductDto;
import com.maltsev.model.request.ProductRequest;
import com.maltsev.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductConverter productConverter;

    @Override
    public List<ProductDto> getProducts() {
        return productRepository.findAll().stream()
                .map(productConverter::convertProductEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductShortInfo saveProduct(ProductRequest productRequest) {
        var productEntity = productConverter.convertRequestToEntity(productRequest);
        var categoryProductEntityList = productConverter.convertCategoryDtoToEntity(productRequest.getCategoryProducts());

        productEntity.setCategoryProducts(categoryProductEntityList);
        categoryProductEntityList.forEach(categoryProductEntity -> categoryProductEntity.getProductEntities().add(productEntity));

        productRepository.save(productEntity);
        return productConverter.convertProductEntityToShortInfo(productEntity);
    }
}
