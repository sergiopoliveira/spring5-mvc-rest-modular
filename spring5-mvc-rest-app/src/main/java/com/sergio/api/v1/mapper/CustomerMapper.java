package com.sergio.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.sergio.domain.Customer;
import com.sergio.model.CustomerDTO;

@Mapper
public interface CustomerMapper {

	CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);
	
	CustomerDTO customerToCustomerDTO(Customer customer);

	Customer customerDtoToCustomer(CustomerDTO customerDTO);
}
