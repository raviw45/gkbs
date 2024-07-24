package com.customer.services;
import java.util.List;

import com.customer.dto.CustomerDto;

public interface CustomerService {

	CustomerDto addCustomer(CustomerDto customer);
	List<CustomerDto> getAllCustomer();
	CustomerDto updateCustomer(String id,CustomerDto customer);
	CustomerDto getSingleCustomer(String id);
	void deleteCustomer(String id);
}
