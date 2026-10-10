package com.gregomebije.cardatabase.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.gregomebije.cardatabase.model.AppUser;

public interface AppUserRepository extends CrudRepository<AppUser, Long> {
	Optional<AppUser> findByUsername(String username);
}