package com.sergio.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sergio.api.v1.mapper.CustomerMapper;
import com.sergio.controllers.v1.CustomerController;
import com.sergio.domain.Customer;
import com.sergio.model.CustomerDTO;
import com.sergio.repositories.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService{

	private final CustomerMapper customerMapper;
	private final CustomerRepository customerRepository;
	
	public CustomerServiceImpl(CustomerMapper customerMapper, CustomerRepository customerRepository) {
		this.customerMapper = customerMapper;
		this.customerRepository = customerRepository;
	}

	@Override
	public List<CustomerDTO> getAllCustomers() {
		
		return customerRepository
				.findAll()
				.stream()
				.map(customer -> {
					CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
					customerDTO.setCustomerUrl(getCustomerUrl(customer.getId()));
					return customerDTO;
				})
				.collect(Collectors.toList());
	}

	@Override
	public CustomerDTO getCustomerById(Long id) {
		return customerRepository.findById(id)
				.map(customerMapper::customerToCustomerDTO)
				.map(customerDTO -> {
					//set API URL
					customerDTO.setCustomerUrl(getCustomerUrl(id));
					return customerDTO;
				})
				.orElseThrow(ResourceNotFoundException::new); 
	}

	@Override
	public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {

		Customer customer = customerMapper.customerDtoToCustomer(customerDTO);
		
		return saveAndReturnDTO(customer);
	}


	@Override
	public CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO) {
		
		Customer customer = customerMapper.customerDtoToCustomer(customerDTO);
		customer.setId(id);
		
		return saveAndReturnDTO(customer);
	}
	
	private CustomerDTO saveAndReturnDTO(Customer customer) {
		
		Customer savedCustomer = customerRepository.save(customer);
		
		CustomerDTO returnDTO = customerMapper.customerToCustomerDTO(savedCustomer);
		
		returnDTO.setCustomerUrl(getCustomerUrl(savedCustomer.getId()));
		
		return returnDTO;
	}

	@Override
	public CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO) {
		return customerRepository.findById(id).map(customer -> {
			
			if(customerDTO.getFirstname() != null) {
				customer.setFirstname(customerDTO.getFirstname());
			}
			
			if(customerDTO.getLastname() != null) {
				customer.setLastname(customerDTO.getLastname());
			}
			
			CustomerDTO returnDTO = customerMapper.customerToCustomerDTO(customerRepository.save(customer));
			
			returnDTO.setCustomerUrl(getCustomerUrl(id));
			
			return returnDTO;
		}).orElseThrow(ResourceNotFoundException::new); 
	}

	@Override
	public void deleteCustomerById(Long id) {
		
		customerRepository.deleteById(id);
	}
	
	private String getCustomerUrl(Long id) {
		return CustomerController.BASE_URL + "/" + id;
	}

}
