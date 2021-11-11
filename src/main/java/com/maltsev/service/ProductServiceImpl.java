package com.maltsev.service;

import com.maltsev.component.converter.ProductConverter;
import com.maltsev.exception.NotFoundProductException;
import com.maltsev.model.ProductShortInfo;
import com.maltsev.model.dto.ProductDto;
import com.maltsev.model.request.ProductRequest;
import com.maltsev.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductConverter productConverter;

    private static final String NOT_FOUND_PRODUCT_ERROR_MESSAGE = "Not found product by id: %s";

    @Override
    public List<ProductDto> getProducts(PageRequest pageRequest) {
        return productRepository.findAll(pageRequest).stream()
                .map(productConverter::convertProductEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductShortInfo saveProduct(ProductRequest productRequest) {
        var productEntity = productConverter.getProductEntity(productRequest);
        productRepository.save(productEntity);
        return productConverter.convertProductEntityToShortInfo(productEntity);
    }

    @Override
    public void deleteById(UUID id) {
        var productEntity = productRepository.findById(id);
        if (productEntity.isPresent()) {
            productRepository.delete(productEntity.get());
        } else {
            throw new NotFoundProductException(String.format(NOT_FOUND_PRODUCT_ERROR_MESSAGE, id));
        }
    }

    @Override
    public void changeProduct(ProductRequest productRequest) {
        var id = productRequest.getId();
        var productEntity = productRepository.findById(id);
        if (productEntity.isPresent()) {
            var productEntitySave = productConverter.getProductEntity(productRequest);
            productRepository.save(productEntitySave);
        } else {
            throw new NotFoundProductException(String.format(NOT_FOUND_PRODUCT_ERROR_MESSAGE, id));
        }
    }

    @Override
    public List<ProductDto> findProductByPrice(int price) {
        return productRepository.findProductByPrice(price).stream()
                .map(productConverter::convertProductEntityToDto)
                .collect(Collectors.toList());
    }
}
