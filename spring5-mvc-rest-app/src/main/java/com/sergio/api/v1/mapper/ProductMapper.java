package com.sergio.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.sergio.api.v1.model.ProductDTO;
import com.sergio.domain.Product;

@Mapper
public interface ProductMapper {
	
	ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
	
	ProductDTO productToProductDTO(Product product);

	Product productDTOtoProduct(ProductDTO productDTO);
}
