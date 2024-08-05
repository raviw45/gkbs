package com.customer.entities;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection="customer_data")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {

      //Customer data
	@Id
	private String id;
	private Title title;
	private CustomerType customerType;
	private String firstName;
	private String lastName;
	
	@Indexed(unique = true)
	private String email;
	private String mobileNumber;
	private String gstinNumber;
	
	@Indexed(unique = true)
	private String panNumber;
	
	@Indexed(unique = true)
	private String adharNumber;
	
     //Other data
	private Date registrationDate;
	private TaxType taxType;
    private ConnectionType connectionType;
    
    private String username;
   
    
    //Fields to add in the future--> service provider, plan, Address, Zone 
}
