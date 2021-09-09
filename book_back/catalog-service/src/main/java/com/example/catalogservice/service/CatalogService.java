package com.example.catalogservice.service;

import com.example.catalogservice.dto.CatalogDto;
import com.example.catalogservice.entity.CatalogEntity;
import com.example.catalogservice.vo.RequestCatalog;

public interface CatalogService {
    Iterable<CatalogEntity> getAllCatalog();
    CatalogEntity getCatalogByProductId(String productId);
    CatalogEntity getCatalogByProductName(String productName);
    Iterable<CatalogEntity> getCatalogByCategory(String category);
    void deleteCatalogById(String productId);
    void deleteCatalogByName(String productName);
    CatalogDto createCatalog(CatalogDto catalogDto);
    CatalogDto updateCatalog(CatalogDto catalogDto, CatalogDto requestCatalog);
}
