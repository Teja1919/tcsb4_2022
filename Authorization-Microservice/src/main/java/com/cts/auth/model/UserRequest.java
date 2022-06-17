package com.cts.auth.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class UserRequest {

	@NotBlank(message = "EMPTY STRING NOT ALLOWED")
	@Size(min = 4, max = 10, message = "Username length should be from 4 to 10 characters")
	private String username;

	@NotBlank(message = "EMPTY STRING NOT ALLOWED")
	private String password;

	public UserRequest(String string, String string2) {
		// TODO Auto-generated constructor stub
	}

	public Object getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getPassword() {
		// TODO Auto-generated method stub
		return null;
	}
}