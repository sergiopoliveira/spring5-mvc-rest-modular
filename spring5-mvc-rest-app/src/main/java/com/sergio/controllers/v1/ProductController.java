package com.sergio.controllers.v1;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sergio.api.v1.model.ProductListDTO;
import com.sergio.service.ProductService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(ProductController.BASE_URL)
public class ProductController {

	public static final String BASE_URL = "/api/v1/products";
	private final ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@ApiOperation(value = "This will get a list of products.")
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ProductListDTO getAllProducts() {
		
		return new ProductListDTO(productService.getAllProducts());
	}
	
}
