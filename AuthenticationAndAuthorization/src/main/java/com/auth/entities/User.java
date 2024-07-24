package com.auth.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="username",unique = true)
	private String username;
	
	@Column(name="fullName")
	private String fullName;
	
	@Column(name="email",unique = true)
	private String email;
	
	@Column(name="phoneNumber")
	private String phoneNumber;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="user_roles",joinColumns = @JoinColumn(name="user_id"),inverseJoinColumns = @JoinColumn(name="role_id"))
	private Set<Role> role=new HashSet<Role>();
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@Column(name="address")
	private String address;
	
	@Column(name="password")
	private String password;
}
