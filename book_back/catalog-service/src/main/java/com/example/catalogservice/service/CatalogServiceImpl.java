package com.example.catalogservice.service;

import com.example.catalogservice.dto.CatalogDto;
import com.example.catalogservice.entity.CatalogEntity;
import com.example.catalogservice.jpa.CatalogRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CatalogServiceImpl implements CatalogService{
    CatalogRepository catalogRepository;

    @Autowired
    public CatalogServiceImpl(CatalogRepository repository, Environment env) {
        this.catalogRepository = repository;
    }
    @Override
    public Iterable<CatalogEntity> getAllCatalog() { return catalogRepository.findAll();}
    @Override
    public CatalogEntity getByProductId(String productId) { return catalogRepository.findByProductId(productId);}
    @Override
    public CatalogEntity getCatalogByProductName(String productName) { return catalogRepository.findByProductName(productName);}
    @Override
    public Iterable<CatalogEntity> getCatalogByCategory(String category) { return catalogRepository.findByCategory(category);}
    @Override
    public void deleteCatalogById(String productId) { catalogRepository.deleteByProductId(productId);}
    @Override
    public void deleteCatalogByName(String productName) { catalogRepository.deleteByProductName(productName);}
    @Override
    public CatalogDto createCatalog(CatalogDto catalogDto) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        CatalogEntity catalogEntity = mapper.map(catalogDto,CatalogEntity.class);
        catalogRepository.save(catalogEntity);
        return null;
    }
    @Override
    public CatalogDto updateCatalog(CatalogDto catalogDto, CatalogDto requestCatalog) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        catalogDto.setProductId(requestCatalog.getProductId());
        catalogDto.setProductName(requestCatalog.getProductName());
        catalogDto.setQty(requestCatalog.getQty());
        catalogDto.setUnitPrice(requestCatalog.getUnitPrice());
        catalogDto.setCategory(requestCatalog.getCategory());
        catalogDto.setTotalPrice(requestCatalog.getTotalPrice());
        CatalogEntity catalogEntity = mapper.map(catalogDto,CatalogEntity.class);
        catalogRepository.save(catalogEntity);
        return null;
    }
}
