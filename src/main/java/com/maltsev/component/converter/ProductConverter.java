package com.maltsev.component.converter;

import com.maltsev.model.ProductShortInfo;
import com.maltsev.model.dto.ProductDto;
import com.maltsev.model.entity.CategoryProductEntity;
import com.maltsev.model.entity.ProductEntity;
import com.maltsev.model.request.CategoryProductRequest;
import com.maltsev.model.request.ProductRequest;
import com.maltsev.model.response.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductConverter {

    ProductDto convertProductEntityToDto(ProductEntity productEntity);

    ProductResponse convertProductDtoToResponse(ProductDto productDto);

    ProductEntity convertRequestToEntity(ProductRequest productRequest);

    ProductShortInfo convertProductEntityToShortInfo(ProductEntity productEntity);

    List<CategoryProductEntity> convertCategoryDtoToEntity(List<CategoryProductRequest> categoryProductRequests);

    default ProductEntity getProductEntity(ProductRequest productRequest) {
        var productEntity = convertRequestToEntity(productRequest);
        var categoryProductEntityList = convertCategoryDtoToEntity(productRequest.getCategoryProducts());
        productEntity.setCategoryProducts(categoryProductEntityList);
        categoryProductEntityList.forEach(categoryProductEntity -> categoryProductEntity.getProductEntities().add(productEntity));
        return productEntity;
    }
}
