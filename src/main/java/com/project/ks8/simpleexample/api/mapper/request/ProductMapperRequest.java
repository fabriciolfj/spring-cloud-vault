package com.project.ks8.simpleexample.api.mapper.request;

import com.project.ks8.simpleexample.api.model.request.ProductDtoRequest;
import com.project.ks8.simpleexample.domain.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapperRequest {

    Product toDomain(final ProductDtoRequest productDtoRequest);
}
