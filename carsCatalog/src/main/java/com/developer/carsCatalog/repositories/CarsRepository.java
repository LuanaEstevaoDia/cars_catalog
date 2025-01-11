package com.developer.carsCatalog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.developer.carsCatalog.entities.Cars;
import com.developer.carsCatalog.entities.Make;

public interface CarsRepository extends JpaRepository<Cars, Long> {
	
	public List<Cars> findByModel(String model);
	
	public List<Cars> findByMake(Make make);
	
	boolean existsByChassi(String chassi);
	
	@Query("FROM Cars c WHERE c.years > :years")
	public List<Cars> findByCarsYears(int years);

}
