package com.sergio.controllers.v1;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sergio.api.v1.model.CustomerDTO;
import com.sergio.api.v1.model.CustomerListDTO;
import com.sergio.service.CustomerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "This is my Customer Controller")
@RestController
@RequestMapping(CustomerController.BASE_URL)
public class CustomerController {

	public static final String BASE_URL = "/api/v1/customers";
	private final CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@ApiOperation(value = "This will get a list of customers.", notes = "These are some notes about the API")
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public CustomerListDTO getAllCustomers() {

		return new CustomerListDTO(customerService.getAllCustomers());
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public CustomerDTO getCustomerById(@PathVariable Long id) {

		return customerService.getCustomerById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CustomerDTO createNewCustomer(@RequestBody CustomerDTO customerDTO) {

		return customerService.createNewCustomer(customerDTO);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public CustomerDTO updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {

		return customerService.saveCustomerByDTO(id, customerDTO);
	}

	@PatchMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public CustomerDTO patchCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {

		return customerService.patchCustomer(id, customerDTO);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteCustomer(@PathVariable Long id) {

		customerService.deleteCustomerById(id);
	}
}
