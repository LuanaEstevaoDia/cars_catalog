package com.developer.carsCatalog.sevices;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developer.carsCatalog.entities.Cars;
import com.developer.carsCatalog.repositories.CarsRepository;

@Service
public class CarsService {

	private static final Logger logger = LoggerFactory.getLogger(CarsService.class);

	@Autowired
	CarsRepository carsRepository;

	public Cars saveCar(Cars car) {
		logger.info("Salvando veículo: {}", car);
		Cars savedCar = carsRepository.save(car);
		logger.info("Car saved: {}", savedCar);
		return savedCar;
	}

	public List<Cars> findAll() {

		return carsRepository.findAll();

	}

	public Optional<Cars> findById(Long id) {
		return carsRepository.findById(id);
	}

	public Boolean deleteById(Long id) {
		Optional<Cars> carDeleted = carsRepository.findById(id);
		if (carDeleted.isPresent()) {
			carsRepository.deleteById(id);
			return true;
		}
		return null;
	}
}
