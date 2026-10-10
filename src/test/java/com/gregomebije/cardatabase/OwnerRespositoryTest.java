package com.gregomebije.cardatabase;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

// 👈 FIX: Update this import to the new Spring Boot 4 package path
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import com.gregomebije.cardatabase.model.Owner;
import com.gregomebije.cardatabase.repository.OwnerRepository;

@DataJpaTest
class OwnerRepositoryTest {
	@Autowired
	private OwnerRepository repository;
	
	@Test
	void saveOwner() {
		repository.save(new Owner("Lucy", "Smith"));
		assertThat(repository.findByFirstname("Lucy").isPresent()).isTrue();
	}

	@Test
	void deleteOwners() {
		repository.save(new Owner("Lisa", "Morrison"));
		repository.deleteAll();
		assertThat(repository.count()).isEqualTo(0);
	}
}
