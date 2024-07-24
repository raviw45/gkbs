package com.customer.dto;

import java.util.Date;

import com.customer.entities.ConnectionType;
import com.customer.entities.CustomerType;
import com.customer.entities.TaxType;
import com.customer.entities.Title;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDto {

	private String id;
	private Title title;
	private CustomerType customerType;
	private String firstName;
	private String lastName;
	private String email;
	private String mobileNumber;
	private String gstinNumber;
	private String panNumber;
	private String adharNumber;
	private Date registrationDate;
	private TaxType taxType;
	private ConnectionType connectionType;
	private String username;
}
