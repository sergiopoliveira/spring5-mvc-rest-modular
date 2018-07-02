package com.sergio.services;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sergio.api.v1.mapper.CustomerMapper;
import com.sergio.bootstrap.Bootstrap;
import com.sergio.domain.Customer;
import com.sergio.model.CustomerDTO;
import com.sergio.repositories.CategoryRepository;
import com.sergio.repositories.CustomerRepository;
import com.sergio.repositories.ProductRepository;
import com.sergio.repositories.VendorRepository;
import com.sergio.service.CustomerService;
import com.sergio.service.CustomerServiceImpl;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerServiceImplTestIT {

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	VendorRepository vendorRepository;
	
	@Autowired
	ProductRepository productRepository;

	CustomerService customerService;

	@Before
	public void setUp() throws Exception {
		System.out.println("Loading Customer Data");
		System.out.println(customerRepository.findAll().size());

		// setup data for testing
		Bootstrap bootstrap = new Bootstrap(categoryRepository, customerRepository, vendorRepository, productRepository);
		bootstrap.run(); // load data

		customerService = new CustomerServiceImpl(CustomerMapper.INSTANCE, customerRepository);
	}

	@Test
	public void patchCustomerUpdateFirstName() throws Exception {
		
		String updateName = "UpdateName";
		long id = getCustomerIdValue();
		
		Customer originalCustomer = customerRepository.getOne(id);
		assertNotNull(originalCustomer);
		//save original first name
		String originalFirstName = originalCustomer.getFirstname();
		String originalLastName = originalCustomer.getLastname();
		
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setFirstname(updateName);
		
		customerService.patchCustomer(id, customerDTO);
		
		Customer updatedCustomer = customerRepository.findById(id).get();
		
		assertNotNull(updatedCustomer);
		assertEquals(updateName, updatedCustomer.getFirstname());
		assertThat(originalFirstName, not(equalTo(updatedCustomer.getFirstname())));
		assertThat(originalLastName, equalTo(updatedCustomer.getLastname()));
	}

	@Test
	public void patchCustomerUpdateLastName() throws Exception {
		
		String updateName = "UpdateName";
		long id = getCustomerIdValue();
		
		Customer originalCustomer = customerRepository.getOne(id);
		assertNotNull(originalCustomer);
		//save original first name
		String originalFirstName = originalCustomer.getFirstname();
		String originalLastName = originalCustomer.getLastname();
		
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setLastname(updateName);
		
		customerService.patchCustomer(id, customerDTO);
		
		Customer updatedCustomer = customerRepository.findById(id).get();
		
		assertNotNull(updatedCustomer);
		assertEquals(updateName, updatedCustomer.getLastname());
		assertThat(originalLastName, not(equalTo(updatedCustomer.getLastname())));
		assertThat(originalFirstName, equalTo(updatedCustomer.getFirstname()));
	}

	private Long getCustomerIdValue() {
		List<Customer> customers = customerRepository.findAll();

		System.out.println("Customers found: " + customers.size());

		// return first id
		return customers.get(0).getId();

	}
}
