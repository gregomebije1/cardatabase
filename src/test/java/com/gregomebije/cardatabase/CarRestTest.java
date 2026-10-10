package com.gregomebije.cardatabase;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach; // 👈 For setting up test data
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.password.PasswordEncoder; // 👈 To encrypt the mock password
import org.springframework.test.web.servlet.MockMvc;

import com.gregomebije.cardatabase.model.AppUser;
import com.gregomebije.cardatabase.repository.AppUserRepository;

@SpringBootTest
@AutoConfigureMockMvc
class CarRestTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private AppUserRepository userRepository; // 👈 Inject user store

	@Autowired
	private PasswordEncoder passwordEncoder; // 👈 Inject encoder bean

	@BeforeEach
	void setUp() {
		// Clear old records to prevent duplicate key constraint violations between test runs
		userRepository.deleteAll();

		// Save a mock admin user with an encrypted password into your testing database H2
		String encryptedPassword = passwordEncoder.encode("admin");
		AppUser testAdmin = new AppUser("admin", encryptedPassword, "ADMIN");
		userRepository.save(testAdmin);
	}

	@Test
	void testAuthentication() throws Exception {
		// Testing authentication with correct credentials
		this.mockMvc
				.perform(post("/login")
						.content("{\"username\":\"admin\",\"password\":\"admin\"}")
						.header(HttpHeaders.CONTENT_TYPE, "application/json"))
				.andDo(print())
				.andExpect(status().isOk());
	}
}
