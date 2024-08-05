package com.service_provider.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceProviderDto {
     private String id;
     private String name;
     private String phoneNumber;
     private String email;
     private String address;
}
