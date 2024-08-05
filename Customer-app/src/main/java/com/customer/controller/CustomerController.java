package com.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.customer.dto.ApiResponse;
import com.customer.dto.CustomerDto;
import com.customer.exceptions.ResourceNotFoundException;
import com.customer.services.CustomerService;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;

    // Add customer to DB
	@PostMapping("/add")
	public ResponseEntity<ApiResponse> addCustomer(@RequestBody CustomerDto customer){
		try {
			CustomerDto savedCustomer=this.customerService.addCustomer(customer);
			
			if(savedCustomer!=null) {
				return ResponseEntity
						.status(HttpStatus.CREATED)
						.body(
						     ApiResponse.builder()
						                .message("Customer Added!!")
						                .status(HttpStatus.CREATED)
						                .customers(List.of(savedCustomer))
						                .build()
						);
			}else {
				throw new Exception("Something went wrong!, Please try again later...");
			}
		} catch (Exception e) {
			System.out.println("Error:=>"+e.getMessage());
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(
							ApiResponse
							  .builder()
							  .message(e.getMessage())
							  .status(HttpStatus.INTERNAL_SERVER_ERROR)
							  .customers(null)
							  .build()
							);
		}
	}
	
    // Get All Customer data
	@GetMapping("/get")
	@PreAuthorize("hasAnyRole('ADMINISTRATOR', 'TECHNICIAN')")
	public ResponseEntity<ApiResponse> getAllCustomers(){
		try {
			List<CustomerDto> allCustomers=this.customerService.getAllCustomer();
			return ResponseEntity.ok(
					ApiResponse.builder()
					            .message("Customer Data")
					            .status(HttpStatus.OK)
					            .customers(allCustomers)
					            .build()
					);
		} catch (Exception e) {
             System.out.println("Error:=>"+e.getMessage());			
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(
					   ApiResponse.builder()
					    .message(e.getMessage())
					    .status(HttpStatus.INTERNAL_SERVER_ERROR)
					    .customers(null)
					    .build()		
						);
		}
	}
	
	
     //	Get Single Customer Data
	@GetMapping("/get/{id}")
	public ResponseEntity<?> getCustomer(@PathVariable("id") String id){
		try {
			CustomerDto customer=this.customerService.getSingleCustomer(id);
			if(customer!=null) {
				return ResponseEntity.ok(
						  ApiResponse
						     .builder()
						     .message("Customer Data!!")
						     .status(HttpStatus.OK)
						     .customers(List.of(customer))
						     .build()
						);
			}else {
				throw new ResourceNotFoundException("Data Not Found!!");
			}
		} catch (Exception e) {
			System.out.println("Exception:=>"+e.getMessage());
			return ResponseEntity
					 .status(HttpStatus.INTERNAL_SERVER_ERROR)
					 .body(
							 ApiResponse
							    .builder()
							    .message(e.getMessage())
							    .status(HttpStatus.INTERNAL_SERVER_ERROR)
							    .customers(null)
							    .build()
							);
		}
	}
	
	
   // Update the customer data
	@PutMapping("/update/{id}")
	public ResponseEntity<ApiResponse> updateCustomer(@PathVariable("id") String id,@RequestBody CustomerDto customer){
		try {
			CustomerDto updatedCustomer=this.customerService.updateCustomer(id, customer);
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(
						   ApiResponse
						      .builder()
						      .message("Customer Updated!!")
						      .status(HttpStatus.OK)
						      .customers(List.of(updatedCustomer))
						      .build()	
					     );
		} catch (Exception e) {
			System.out.println("Exception:=>"+e.getMessage());
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(
						  ApiResponse
						      .builder()
						      .message(e.getMessage())
						      .status(HttpStatus.INTERNAL_SERVER_ERROR)
						      .customers(null)
						      .build()	
						);
		}
	}
	
	
    //	delete customer
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ApiResponse> deleteCustomer(@PathVariable("id") String id){
		try {
			this.customerService.deleteCustomer(id);
			return ResponseEntity.ok(ApiResponse.builder().message("Customer Deleted!!").status(HttpStatus.OK).build());
		} catch (Exception e) {
			System.out.println("Exception:=>"+e.getMessage());
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(ApiResponse
						      .builder()
						      .message(e.getMessage())
						      .status(HttpStatus.INTERNAL_SERVER_ERROR)
						      .customers(null)
						      .build()	
						);
		}
	}
	
	
}
