package com.service_provider.dto;

import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse {

	private String message;
	private boolean success;
	private HttpStatus statusCode;
	private List<ServiceProviderDto> serviceProviders;
}
