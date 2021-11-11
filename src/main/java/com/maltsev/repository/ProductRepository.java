package com.maltsev.repository;

import com.maltsev.model.entity.ProductEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends PagingAndSortingRepository<ProductEntity, UUID> {
    Optional<ProductEntity> findProductByPrice(int cost);

}
