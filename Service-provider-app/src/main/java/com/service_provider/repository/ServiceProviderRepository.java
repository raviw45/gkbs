package com.service_provider.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.service_provider.entities.ServiceProvider;

@Repository
public interface ServiceProviderRepository extends MongoRepository<ServiceProvider, String> {

}
