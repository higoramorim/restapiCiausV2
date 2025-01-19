package com.ciaus.restapiCiaus.mapper;

import com.ciaus.restapiCiaus.dto.ProductDto;
import com.ciaus.restapiCiaus.model.Product;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ProductMapper {
    public static ProductDto mapToDto(Product product){
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setName(product.getName());

        return dto;
    }
}
