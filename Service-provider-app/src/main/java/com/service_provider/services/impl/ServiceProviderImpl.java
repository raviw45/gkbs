package com.service_provider.services.impl;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.service_provider.dto.ServiceProviderDto;
import com.service_provider.entities.ServiceProvider;
import com.service_provider.exceptions.ResourceNotFoundException;
import com.service_provider.repository.ServiceProviderRepository;
import com.service_provider.services.ServiceProviderServices;
@Service
public class ServiceProviderImpl implements ServiceProviderServices {

	@Autowired
	private ServiceProviderRepository providerRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public ServiceProviderDto add(ServiceProviderDto serviceProvider) {
		ServiceProvider savedServiceProvider=this.providerRepository.save(this.modelMapper.map(serviceProvider, ServiceProvider.class));
		return this.modelMapper.map(savedServiceProvider, ServiceProviderDto.class);
	}

	@Override
	public ServiceProviderDto get(String id) {
		ServiceProvider serviceProvider=this.providerRepository.findById(id).get();
		return this.modelMapper.map(serviceProvider, ServiceProviderDto.class);
	}

	@Override
	public List<ServiceProviderDto> getAll() {
	    List<ServiceProvider> serviceProviders=this.providerRepository.findAll();
	    List<ServiceProviderDto> getServices=serviceProviders.stream()
	                    .map(this::convertToDto)
	                    .collect(Collectors.toList());
		return getServices;
	}
	
	public ServiceProviderDto convertToDto(ServiceProvider serviceProvider) {
		return this.modelMapper.map(serviceProvider, ServiceProviderDto.class);
	}

	@Override
	public ServiceProviderDto update(String id,ServiceProviderDto serviceProvider) {
		if(this.providerRepository.existsById(id)) {
			ServiceProvider serviceProviderData=this.modelMapper.map(serviceProvider, ServiceProvider.class);
			serviceProviderData.setId(id);
		  return this.modelMapper.map(this.providerRepository.save(serviceProviderData), ServiceProviderDto.class);
		}else {			
			return null;
		}
	}

	@Override
	public void deleteServiceProvider(String id) throws Exception {
		if(this.providerRepository.existsById(id)) {
			this.providerRepository.deleteById(id);
		}else {
			throw new ResourceNotFoundException("Data not found!!!");
		}
	}

}
