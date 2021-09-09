package com.example.catalogservice.controller;

import com.example.catalogservice.dto.CatalogDto;
import com.example.catalogservice.entity.CatalogEntity;
import com.example.catalogservice.service.CatalogService;
import com.example.catalogservice.vo.RequestCatalog;
import com.example.catalogservice.vo.ResponseCatalog;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
@Slf4j
public class CatalogController {
    private Environment env;
    CatalogService catalogService;

    @Autowired
    public CatalogController(Environment env, CatalogService catalogService) {
        this.env = env;
        this.catalogService = catalogService;
    }
    @GetMapping("/health_check")
    public String status(HttpServletRequest request) {
        return String.format("It`s Working in Catalog Service on port %s",request.getServerPort());
    }
    @GetMapping("/catalogs")
    public ResponseEntity<List<ResponseCatalog>> getCatalogs() {
        Iterable<CatalogEntity> orderList = catalogService.getAllCatalog();
        List<ResponseCatalog> result = new ArrayList<>();
        orderList.forEach(v -> {
            result.add(new ModelMapper().map(v,ResponseCatalog.class));
        });
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @GetMapping("/catalogs/pid/{productId}")
    public ResponseEntity<ResponseCatalog> getCatalogByproductId(@PathVariable("productId") String productId) {
        CatalogEntity catalogEntity = catalogService.getByProductId(productId);

        if (catalogEntity != null) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ModelMapper().map(catalogEntity, ResponseCatalog.class));
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/catalogs/pn/{productName}")
    public ResponseEntity<ResponseCatalog> getCatalogByproductName(@PathVariable("productName") String productName) {
        CatalogEntity catalogEntity = catalogService.getCatalogByProductName(productName);

        if (catalogEntity != null) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ModelMapper().map(catalogEntity, ResponseCatalog.class));
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/catalogs/{category}")
    public ResponseEntity<List<ResponseCatalog>> getCatalogByCategory(@PathVariable("category") String category) {
        Iterable<CatalogEntity> catalogList = catalogService.getCatalogByCategory(category);
        List<ResponseCatalog> result = new ArrayList<>();

        if (catalogList != null) {
            catalogList.forEach(v -> {
                result.add(new ModelMapper().map(v,ResponseCatalog.class));
            });
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/catalogs/{productId}")
    public void deleteCatalogById(@PathVariable("productId") String productId) {
        catalogService.deleteCatalogById(productId);
    }
    @DeleteMapping("/catalogs/{productName}")
    public void deleteCatalogByName(@PathVariable("productName") String productName) {
        catalogService.deleteCatalogByName(productName);
    }
    @PostMapping("/catalogs")
    public ResponseEntity createCatalog(@RequestBody @Valid RequestCatalog catalog) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        CatalogDto catalogDto = mapper.map(catalog,CatalogDto.class);
        catalogService.createCatalog(catalogDto);
        ResponseCatalog responseCatalog = mapper.map(catalogDto,ResponseCatalog.class);

         return ResponseEntity.status(HttpStatus.CREATED).body(responseCatalog);
    }
    @PutMapping("/catalogs/{productId}")
    public ResponseEntity<ResponseCatalog> updateCatalog(@PathVariable("productId") String productId, @RequestBody @Valid RequestCatalog requestCatalog) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        CatalogDto catalogDto = mapper.map(catalogService.getByProductId(productId),CatalogDto.class);
        CatalogDto requestDto = mapper.map(requestCatalog,CatalogDto.class);
        catalogService.updateCatalog(catalogDto, requestDto);
        ResponseCatalog responseCatalog = mapper.map(catalogDto,ResponseCatalog.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseCatalog);
    }
}