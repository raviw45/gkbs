package com.service_provider.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service_provider.dto.ApiResponse;
import com.service_provider.dto.ServiceProviderDto;
import com.service_provider.services.ServiceProviderServices;

@RestController
@RequestMapping("/api/service-provider")
public class ServiceProviderController {

	@Autowired
	private ServiceProviderServices providerServices;
	
//	Add Service Provider to the DB
	@PostMapping("/save")
	public ResponseEntity<ApiResponse> add(@RequestBody ServiceProviderDto serviceProvider){
		   try {
			ServiceProviderDto savedServiceProvider=this.providerServices.add(serviceProvider);
			if(savedServiceProvider != null) {				
				return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.builder()
						.message("Service Provider Added!!")
						.statusCode(HttpStatus.CREATED)
						.success(true)
						.serviceProviders(List.of(savedServiceProvider))
						.build());
			}else {
				throw new Exception("Something went wrong!!, Please try again later...");
			}
		} catch (Exception e) {
			System.out.println("Exception:"+e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.builder()
					.message(e.getMessage())
					.statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
					.success(false)
					.serviceProviders(null)
					.build());
		  }	
	}
	
//	Get all service providers list
	@GetMapping("/getAll")
	public ResponseEntity<ApiResponse> getAll(){
		try {
			List<ServiceProviderDto> providers= this.providerServices.getAll();
			return ResponseEntity.ok(ApiResponse.builder()
					.message("All Service Providers List!!")
					.success(true)
					.statusCode(HttpStatus.OK)
					.serviceProviders(providers)
					.build());
		} catch (Exception e) {
			System.out.println("Exception:"+e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.builder()
					.message(e.getMessage())
					.statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
					.success(false)
					.serviceProviders(null)
					.build());
		}
	}
	
	
//	Update service provider data
	@PutMapping("/update/{id}")
	public ResponseEntity<ApiResponse> update(@PathVariable("id") String id,@RequestBody ServiceProviderDto serviceProvider){
		  try {
			ServiceProviderDto updatedProvider=this.providerServices.update(id, serviceProvider);
			return ResponseEntity.ok(ApiResponse.builder()
					.message("Service Provider Updated!!")
					.statusCode(HttpStatus.OK)
					.success(true)
					.serviceProviders(List.of(updatedProvider))
					.build());
		} catch (Exception e) {
			System.out.println("Exception:"+e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.builder()
					.message(e.getMessage())
					.statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
					.success(false)
					.serviceProviders(null)
					.build());
		}	
	}
	
//	Delete service provider
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") String id){
		try {
			this.providerServices.deleteServiceProvider(id);
			return ResponseEntity.ok(ApiResponse.builder()
					.message("Service Provider Deleted!!")
					.statusCode(HttpStatus.OK)
					.success(true)
					.serviceProviders(null)
					.build());
		} catch (Exception e) {
			System.out.println("Exception:"+e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.builder()
					.message(e.getMessage())
					.statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
					.success(false)
					.serviceProviders(null)
					.build());
		}
	}
}
