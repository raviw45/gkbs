package com.customer.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.customer.entities.Customer;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {

}
