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
import org.springframework.web.bind.annotation.RestController;

import com.developer.carsCatalog.entities.Cars;
import com.developer.carsCatalog.sevices.CarsService;

@RestController
@RequestMapping("/car")
public class CarsController {

	private static final Logger logger = LoggerFactory.getLogger(CarsController.class);

	@Autowired
	CarsService carsService;

	@PostMapping("/save")
	public Cars saveCar(@RequestBody Cars car) {
		logger.info("Received request to save car: {}", car);
		return carsService.saveCar(car);

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
				upDatedCar.setManufacturer(carDetails.getManufacturer());
				upDatedCar.setModel(carDetails.getModel());
				upDatedCar.setYears(carDetails.getYears());

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


}
