package com.sergio.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sergio.api.v1.mapper.ProductMapper;
import com.sergio.api.v1.model.ProductDTO;
import com.sergio.controllers.v1.ProductController;
import com.sergio.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{

	private final ProductMapper productMapper;
	private final ProductRepository productRepository;
	
	public ProductServiceImpl(ProductMapper productMapper, ProductRepository productRepository) {
		this.productMapper = productMapper;
		this.productRepository = productRepository;
	}

	@Override
	public List<ProductDTO> getAllProducts() {
		
		return productRepository
				.findAll()
				.stream()
				.map(product -> {
					ProductDTO productDTO = productMapper.productToProductDTO(product);
					productDTO.setProductUrl(getProductUrl(product.getId()));
					return productDTO;
				})
				.collect(Collectors.toList());
	}
	
	private String getProductUrl(Long id) {
		return ProductController.BASE_URL + "/" + id;
	}
}
