package com.service_provider.services;

import java.util.List;

import com.service_provider.dto.ServiceProviderDto;

public interface ServiceProviderServices {

	  ServiceProviderDto add(ServiceProviderDto serviceProvider);
	  ServiceProviderDto get(String id);
	  List<ServiceProviderDto> getAll();
	  ServiceProviderDto update(String id,ServiceProviderDto serviceProvider);
	  void deleteServiceProvider(String id) throws Exception;
}
