package com.project.ks8.simpleexample.api.mapper.response;

import com.project.ks8.simpleexample.api.model.response.ProductDtoResponse;
import com.project.ks8.simpleexample.domain.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapperResponse {

    ProductDtoResponse toDto(final Product product);
}
