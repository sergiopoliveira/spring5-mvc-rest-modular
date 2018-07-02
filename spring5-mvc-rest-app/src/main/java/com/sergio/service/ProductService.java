package com.sergio.service;

import java.util.List;

import com.sergio.api.v1.model.ProductDTO;

public interface ProductService {

	List<ProductDTO> getAllProducts();
}
