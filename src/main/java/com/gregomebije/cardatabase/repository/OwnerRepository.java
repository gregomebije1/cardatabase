package com.gregomebije.cardatabase.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

import com.gregomebije.cardatabase.model.Owner;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
    Optional<Owner> findByFirstname(String firstname);
}