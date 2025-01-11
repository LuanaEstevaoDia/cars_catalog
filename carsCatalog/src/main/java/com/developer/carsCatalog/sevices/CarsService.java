package com.developer.carsCatalog.sevices;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.developer.carsCatalog.entities.Cars;
import com.developer.carsCatalog.entities.Make;
import com.developer.carsCatalog.entities.StatusCar;
import com.developer.carsCatalog.repositories.CarsRepository;


@Service
public class CarsService {

	private static final Logger logger = LoggerFactory.getLogger(CarsService.class);

	@Autowired
	CarsRepository carsRepository;

	



	public Cars saveCar(Cars car) {
		validateCar(car);
		return carsRepository.save(car);
		

	}

	public List<Cars> findAll() {

		return carsRepository.findAll();

	}

	public Optional<Cars> findById(Long id) {
		return carsRepository.findById(id);
	}
	
	public  String update(Long id, Cars car) {
		if(!carsRepository.existsById(id)){
			throw new NoSuchElementException("Veículo não encontrado");
		}
		
		car.setId(id);
		validateCar(car);
		this.carsRepository.save(car);
		return car.getModel() + "Atualização realizada com sucesso";
	}

	public Boolean deleteById(Long id) {
		Optional<Cars> carDeleted = carsRepository.findById(id);
		if (carDeleted.isPresent()) {
			carsRepository.deleteById(id);
			return true;
		}
		return false;
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
	public void validateCar(Cars car) {
		if(car.getModel() == null ||car.getModel().isEmpty()){
			throw new IllegalArgumentException("O Campo modelo é obrigatório");
			
		}
		int currentYear = LocalDate.now().getYear();
		if(car.getYears() < 1900 || car.getYears() > currentYear) {
			throw new IllegalArgumentException("Ano do veículo inválido");
		}
		if(car.getChassi() == null || car.getChassi().length() != 17) {
			throw new IllegalArgumentException("Número de chassi enválido");
		}
		if(carsRepository.existsByChassi(car.getChassi())) {
			throw new DuplicateKeyException("Chassi já existente");
		}
		if(car.getStatus() == StatusCar.VENDIDO) {
			throw new IllegalStateException("Veículo vendido não pode ser atualizado");
		}
	
		car.setPrice(calculatePrice(car));
		
	}
		
	private Double calculatePrice(Cars car) {
		double basePrice = 20000;
		int currentYear = LocalDate.now().getYear();
		if(car.getYears()< currentYear - 10) {
			basePrice *= 0.8;
		}
		return basePrice;
		
	}
	
}
