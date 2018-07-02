package com.sergio.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ProductDTO {

	@ApiModelProperty(value = "This is my first name", required = true)
	private String name;

	@JsonProperty("product_url")
	private String productUrl;
}
