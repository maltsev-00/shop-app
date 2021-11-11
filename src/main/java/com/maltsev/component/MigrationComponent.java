package com.maltsev.component;

import com.maltsev.model.entity.CategoryProductEntity;
import com.maltsev.model.entity.ManufacturerProductEntity;
import com.maltsev.model.entity.ProductEntity;
import com.maltsev.model.entity.ProductPhotoEntity;
import com.maltsev.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class MigrationComponent {

    private final ProductRepository productRepository;
    private static final int COUNT_PRODUCT_MIGRATION = 500;

    @EventListener(ContextRefreshedEvent.class)
    public void contextRefreshedMigration() {
        var productEntityList = new ArrayList<ProductEntity>();
        for (int i = 0; i < COUNT_PRODUCT_MIGRATION; i++) {
            var productEntity = new ProductEntity();
            productEntity.setId(UUID.randomUUID());
            productEntity.setName("Name" + i);
            productEntity.setPrice(1 + i);
            productEntity.setDescription("Description" + i);
            productEntity.setProductPhoto(new ProductPhotoEntity(UUID.randomUUID(), "Url" + i));
            productEntity.setCategoryProducts(Collections.singletonList(new CategoryProductEntity(UUID.randomUUID(), "Name" + i,
                    Collections.singleton(productEntity))));
            productEntity.setManufacturerProduct(Collections.singletonList(new ManufacturerProductEntity(UUID.randomUUID(), "Name" + i)));
            productEntityList.add(productEntity);
        }
        productRepository.saveAll(productEntityList);
    }

}
