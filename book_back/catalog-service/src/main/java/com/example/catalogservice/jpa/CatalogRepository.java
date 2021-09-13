package com.example.catalogservice.jpa;

import com.example.catalogservice.entity.CatalogEntity;
import org.springframework.data.repository.CrudRepository;


public interface CatalogRepository extends CrudRepository<CatalogEntity,Long> {
    CatalogEntity findByProductId(String productId);

    CatalogEntity findByProductName(String productName);
    Iterable<CatalogEntity> findByCategory(String category);
    void deleteByProductId(String productId);
    void deleteByProductName(String productName);
}
