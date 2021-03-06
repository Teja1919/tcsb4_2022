package com.cts.auth.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.cts.auth.controller.log;
import com.cts.auth.exception.InvalidCredentialsException;
import com.cts.auth.model.User;
import com.cts.auth.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class UserServiceTests {

	@Autowired
	private UserServiceImpl userServiceImpl;

	@MockBean
	private UserRepository userRepository;

	@Test
	@DisplayName("Method to test LoadUserByUsername() method when username is valid")
	void testLoadUserByUsername_validUsername() {
		log.info("TEST EXECUTION START - testLoadUserByUsername_validUsername()");

		// Data to mock
		User user = new User("admin", "$2a$12$t.1BTGVYIkKe8S5H6BC9N.sAmNp0DytK03BqEnZc63fObgeGqvQh.");

		// Convert to optional
		Optional<User> userOptional = Optional.of(user);

		// user-name to check - correct credentials
		final String id = "admin";

		// actual value
		SecurityUser securityUser = new SecurityUser(id, user.getPassword(),
				Collections.singletonList(new SimpleGrantedAuthority("ADMIN")));

		// Mock the repository
		when(userRepository.findById(id)).thenReturn(userOptional);

		log.info("Executing the test case...");
		// checking condition
		assertEquals(userServiceImpl.loadUserByUsername(id), securityUser);
		assertNotNull(securityUser);

		log.info("TEST EXECUTION END - testLoadUserByUsername_validUsername()");
	}

	@Test
	@DisplayName("Method to test LoadUserByUsername() method when username is valid")
	void testLoadUserByUsername_invalidUsername() {
		log.info("TEST EXECUTION START - testLoadUserByUsername_invalidUsername()");

		User user = new User("admin", "$2a$12$t.1BTGVYIkKe8S5H6BC9N.sAmNp0DytK03BqEnZc63fObgeGqvQh.");

		// Data to mock
		Optional<User> userOptional = Optional.empty();

		// user-name to check - incorrect credentials
		final String id = "admin1";

		// actual value
		SecurityUser securityUser = new SecurityUser(id, user.getPassword(),
				Collections.singletonList(new SimpleGrantedAuthority("ADMIN")));

		// Mock the repository
		when(userRepository.findById(id)).thenReturn(userOptional);

		log.info("Running the test case...");
		// checking condition
		InvalidCredentialsException thrownException = assertThrows(InvalidCredentialsException.class,
				() -> userServiceImpl.loadUserByUsername(id));

		assertTrue(thrownException.getMessage().contains("USER NOT PRESENT"));
		assertNotNull(securityUser);

		log.info("TEST EXECUTION END - testLoadUserByUsername_invalidUsername()");
	}

	// Class to avoid User conflict
	@MockBean
	public class SecurityUser extends org.springframework.security.core.userdetails.User {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public SecurityUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
			super(username, password, authorities);
		}

	}
}
