package com.developer.carsCatalog.sevices;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developer.carsCatalog.entities.Cars;
import com.developer.carsCatalog.entities.Make;
import com.developer.carsCatalog.repositories.CarsRepository;


@Service
public class CarsService {

	private static final Logger logger = LoggerFactory.getLogger(CarsService.class);

	@Autowired
	CarsRepository carsRepository;

	



	public Cars saveCar(Cars car) {
		
		return carsRepository.save(car);
		

	}

	public List<Cars> findAll() {

		return carsRepository.findAll();

	}

	public Optional<Cars> findById(Long id) {
		return carsRepository.findById(id);
	}
	
	public  String update(Long id, Cars car) {
		car.setId(id);
		this.carsRepository.save(car);
		return car.getModel() + "Atualização realizada com sucesso";
	}

	public Boolean deleteById(Long id) {
		Optional<Cars> carDeleted = carsRepository.findById(id);
		if (carDeleted.isPresent()) {
			carsRepository.deleteById(id);
			return true;
		}
		return null;
	}
	
	public List<Cars> findByModel(String model){
		return this.carsRepository.findByModel(model);
		
	}
	public List<Cars> findByMake(Long idMake){
		Make make = new Make();
		make.setId(idMake);
		return this.carsRepository.findByMake(make);
}

	public List<Cars> findByCarsYears(int years){
		return this.carsRepository.findByCarsYears(years);
		
	}
	
}
