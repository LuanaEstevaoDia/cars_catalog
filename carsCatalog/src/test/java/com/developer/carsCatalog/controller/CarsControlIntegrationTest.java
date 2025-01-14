package com.developer.carsCatalog.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.developer.carsCatalog.CarsCatalogApplication;
import com.developer.carsCatalog.controllers.CarsController;
import com.developer.carsCatalog.entities.Cars;
import com.developer.carsCatalog.entities.Make;
import com.developer.carsCatalog.entities.Owner;
import com.developer.carsCatalog.entities.StatusCar;
import com.developer.carsCatalog.repositories.CarsRepository;

@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration(classes = CarsCatalogApplication.class)
public class CarsControlIntegrationTest {

	@Autowired
	CarsController carsController;

	@MockitoBean
	CarsRepository carsRepository;

	private Cars car1;
	private Cars car2;
	private Cars car3;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

		List<Cars> carList = new ArrayList<>();

		Make makeToyota = new Make(1L, "Toyota", "12345678901234", new ArrayList<>());
		Make makeHonda = new Make(2L, "Honda", "23456789012345", new ArrayList<>());
		Make makeFiat = new Make(3L, "Fiat", "34567890123456", new ArrayList<>());
		
		List<Owner> ownerList1 = new ArrayList<>();
		Owner owner1A = new Owner(1L, "Alice", 30);
		Owner owner2A = new Owner(2L, "Bob", 40);
		Owner owner3A = new Owner(3L,"Mathias",25);
		
		ownerList1.add(owner1A);
		ownerList1.add(owner2A);
		ownerList1.add(owner3A);
		

		List<Owner> ownerList2 = new ArrayList<>();
		Owner owner1B= new Owner(1L, "Maria", 30);
		Owner owner2B = new Owner(2L, "Pablo", 40);
		Owner owner3B = new Owner(3L,"Helen",25);
		
		ownerList2.add(owner1B);
		ownerList2.add(owner2B);
		ownerList2.add(owner3B);
		

		List<Owner> ownerList3 = new ArrayList<>();
		Owner owner1C= new Owner(1L, "Regis", 30);
		Owner owner2C = new Owner(2L, "Marlon", 40);
		Owner owner3C = new Owner(3L,"Angela",25);
		
		ownerList3.add(owner1C);
		ownerList3.add(owner2C);
		ownerList3.add(owner3C);

		car1 = Cars.builder()
				.id(1L)
				.model("Corolla")
				.years(2020)
				.chassi("1NXBR32E76Z123456")
				.make(makeToyota)
				.owners(ownerList1)
				.status(StatusCar.DISPONIVEL)
				.build();
				
				

		car2= Cars.builder()
				.id(2L)
				.model("Fit")
				.years(2022)
				.chassi("1NXBR32E76Z123789")
				.make(makeHonda)
				.owners(ownerList2)
				.status(StatusCar.DISPONIVEL)
				.build();
		

		car3= Cars.builder()
				.id(3L)
				.model("Punto")
				.years(2020)
				.chassi("1NXBR32E76Z123196")
				.make(makeFiat)
				.owners(ownerList3)
				.status(StatusCar.DISPONIVEL)
				.build();


		carList.add(car1);
		carList.add(car2);
		carList.add(car3);

		when(carsRepository.findAll()).thenReturn(carList);
	}

	@Test
	void testFindAll1() {
		ResponseEntity<List<Cars>> answer = this.carsController.getAllCars();
		assertEquals(HttpStatus.OK, answer.getStatusCode());
	}

}
