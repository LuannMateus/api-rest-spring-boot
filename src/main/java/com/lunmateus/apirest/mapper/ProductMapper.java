package com.lunmateus.apirest.mapper;

import com.lunmateus.apirest.dto.models.ProductDTO;
import com.lunmateus.apirest.models.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    Product toModel(ProductDTO productDTO);

    ProductDTO toDTO(Product product);
}
