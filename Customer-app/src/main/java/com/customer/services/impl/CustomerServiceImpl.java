package com.customer.services.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.customer.dto.CustomerDto;
import com.customer.entities.Customer;
import com.customer.repository.CustomerRepository;
import com.customer.services.CustomerService;
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CustomerDto addCustomer(CustomerDto customer) {
		 Customer customerData=this.modelMapper.map(customer, Customer.class);
		 System.out.println(customerData);
		  Customer savedCustomer=this.customerRepository.save(customerData);
		return this.modelMapper.map(savedCustomer,CustomerDto.class);
	}

	@Override
	public List<CustomerDto> getAllCustomer() {
		List<Customer> allCustomers =this.customerRepository.findAll();
		return allCustomers.stream()
				.map(this::convertToDto)
				.collect(Collectors.toList());
				
	}
	
	private CustomerDto convertToDto(Customer customer) {
		return this.modelMapper.map(customer, CustomerDto.class);
	}

	@Override
	public CustomerDto updateCustomer(String id,CustomerDto customer) {
		if(this.customerRepository.existsById(id)) {
			Customer customerData=this.modelMapper.map(customer, Customer.class);
			customerData.setId(id);
			return convertToDto(this.customerRepository.save(customerData));
		}else {			
			return null;
		}
	}

	@Override
	public CustomerDto getSingleCustomer(String id) {
		Customer customer=this.customerRepository.findById(id).get();
		return this.modelMapper.map(customer, CustomerDto.class);
	}

	@Override
	public void deleteCustomer(String id) {
		this.customerRepository.deleteById(id);
	}

}
