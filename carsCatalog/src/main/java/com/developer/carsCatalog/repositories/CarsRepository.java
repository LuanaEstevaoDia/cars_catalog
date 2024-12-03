package com.developer.carsCatalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.developer.carsCatalog.entities.Cars;

public interface CarsRepository extends JpaRepository<Cars, Long> {

}
