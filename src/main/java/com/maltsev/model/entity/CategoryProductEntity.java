package com.maltsev.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "category_product")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;


    @ManyToMany(mappedBy = "categoryProducts", cascade = {CascadeType.ALL})
    private Set<ProductEntity> productEntities = new HashSet<>();

}
