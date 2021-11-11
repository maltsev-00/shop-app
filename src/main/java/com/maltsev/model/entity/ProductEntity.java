package com.maltsev.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;
    private String description;
    private int price;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "manufacturer_product_id")
    private List<ManufacturerProductEntity> manufacturerProduct;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_photo_id", referencedColumnName = "id")
    private ProductPhotoEntity productPhoto;


    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(name = "products_category", joinColumns = {@JoinColumn(name = "product_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")})
    private List<CategoryProductEntity> categoryProducts;
}
