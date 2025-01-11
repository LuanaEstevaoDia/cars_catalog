package com.developer.carsCatalog.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.developer.carsCatalog.entities.Cars;
import com.developer.carsCatalog.entities.Make;
import com.developer.carsCatalog.entities.StatusCar;
import com.developer.carsCatalog.sevices.CarsService;

@RestController
@RequestMapping("/car")
public class CarsController {

	private static final Logger logger = LoggerFactory.getLogger(CarsController.class);

	@Autowired
	CarsService carsService;

	@PostMapping("/save")
	public ResponseEntity<Cars> saveCar(@RequestBody Cars car) {
		try {
			logger.info("Received request to save car: {}", car);
			Cars savedCar = carsService.saveCar(car);
			logger.info("Car saved successfully: {}", savedCar);
			return new ResponseEntity<>(savedCar, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error saving car", e);
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

		}

	}

	@GetMapping("/all")
	public ResponseEntity<List<Cars>> getAllCars() {
		try {
			List<Cars> cars = carsService.findAll();
			if (cars.isEmpty()) {
				return new ResponseEntity<>(cars, HttpStatus.NO_CONTENT);
			} else {

				return new ResponseEntity<>(cars, HttpStatus.OK);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/{id}")
	public ResponseEntity<Cars> getCarById(@PathVariable Long id) {
		try {
			Optional<Cars> carData = carsService.findById(id);
			if (carData.isPresent()) {
				return new ResponseEntity<>(carData.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Cars> upDateCars(@PathVariable Long id, @RequestBody Cars carDetails) {
		try {
			Optional<Cars> carData = carsService.findById(id);
			if (carData.isPresent()) {
				Cars upDatedCar = carData.get();

				upDatedCar.setModel(carDetails.getModel());
				upDatedCar.setYears(carDetails.getYears());
				upDatedCar.setChassi(carDetails.getChassi());
				upDatedCar.setMake(carDetails.getMake());
				upDatedCar.setOwners(carDetails.getOwners());
				upDatedCar.setStatus(carDetails.getStatus());

				carsService.validateCar(upDatedCar);
				return new ResponseEntity<>(carsService.saveCar(upDatedCar), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteCar(@PathVariable Long id) {
		try {
			boolean deleted = carsService.deleteById(id);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", deleted);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/findByModel")
	public ResponseEntity<List<Cars>> findByModel(@RequestParam String model) {
		try {
			logger.info("Finding cars by model: {}", model);
			List<Cars> cars = carsService.findByModel(model);
			logger.info("Found {} cars", cars.size());
			return new ResponseEntity<>(cars, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error finding cars by model: " + model, e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/findByMake")
	public ResponseEntity<List<Cars>> findByMake(@RequestParam Long idMake) {
		try {
			logger.info("Finding cars by model: {}", idMake);
			List<Cars> cars = carsService.findByMake(idMake);
			logger.info("Found {} cars", cars.size());
			return new ResponseEntity<>(cars, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error finding cars by model: " + idMake, e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/findByYears")
	public ResponseEntity<List<Cars>> findByCarsYears(@RequestParam int years) {
		try {
			logger.info("Finding cars by model: {}", years);
			List<Cars> cars = carsService.findByCarsYears(years);
			logger.info("Found {} cars", cars.size());
			return new ResponseEntity<>(cars, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error finding cars by model: " + years, e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

}
