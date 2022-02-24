package com.project.ks8.simpleexample.api.controller;

import com.project.ks8.simpleexample.api.ResourceUriHelper;
import com.project.ks8.simpleexample.api.mapper.request.ProductMapperRequest;
import com.project.ks8.simpleexample.api.mapper.response.ProductMapperResponse;
import com.project.ks8.simpleexample.api.model.request.ProductDtoRequest;
import com.project.ks8.simpleexample.api.model.response.ProductDtoResponse;
import com.project.ks8.simpleexample.domain.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RequestMapping("/products")
@RestController
@RequiredArgsConstructor
public class ProductController {
    
    private final ProductService service;
    private final ProductMapperRequest request;
    private final ProductMapperResponse response;
    
    @GetMapping
    public List<ProductDtoResponse> findAll() {
        return service.findAll().stream()
                .map(p -> response.toDto(p)).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductDtoResponse findBy(@PathVariable("id") final Long id) {
        return Optional.of(service.findlById(id)).map(p -> response.toDto(p)).get();
    }

    @PostMapping
    public void create(@RequestBody ProductDtoRequest dto) {
        var product = service.create(request.toDomain(dto));
        ResourceUriHelper.addUriResponseHeader(product);
    }

    @PutMapping("/{id}")
    public ProductDtoResponse update(@PathVariable("id")final Long id, @RequestBody ProductDtoRequest dto) {
        return Optional.of(service.update(id, request.toDomain(dto)))
                .map(p -> response.toDto(p)).get();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") final Long id) {
        service.delete(id);
    }
}
