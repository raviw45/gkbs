package com.service_provider.entities;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection="service-provider")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceProvider {
    @Id
	private String id;
    
    @Indexed(unique=true)
	private String name;
    
	private String phoneNumber;
	
	@Indexed(unique = true)
	private String email;
	
	private String address;
	
   //API URL from synnefo and other companies
}
