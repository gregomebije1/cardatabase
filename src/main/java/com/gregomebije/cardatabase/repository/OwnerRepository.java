package com.gregomebije.cardatabase.repository;


import org.springframework.data.repository.CrudRepository;

import com.gregomebije.cardatabase.model.Owner;

public interface OwnerRepository extends CrudRepository<Owner, Long> {

}