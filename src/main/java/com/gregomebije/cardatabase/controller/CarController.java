package com.gregomebije.cardatabase.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.Resource;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;


import com.gregomebije.cardatabase.model.Car;
import com.gregomebije.cardatabase.repository.CarRepository;

@RestController
//@RequestMapping("/api")
public class CarController {
	private final CarRepository repository;

	public CarController(CarRepository repository) {
		this.repository = repository;
	}

	@PreAuthorize("hasRole('USER')")
	@GetMapping("/cars")
	public Iterable<Car> getCars() {
		return repository.findAll();
	}

	

    //@GetMapping("/cars/{id}") => 20
    
    //@GetMapping("/cars/{id}/owner")

    //api/cars/search/findByBrand?
}