package com.developer.carsCatalog.controller;

	

	import static org.junit.jupiter.api.Assertions.assertEquals;
	import static org.mockito.Mockito.when;

	import java.util.ArrayList;
	import java.util.List;

	import org.junit.jupiter.api.BeforeEach;
	import org.junit.jupiter.api.Test;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.boot.test.context.SpringBootTest;
	import org.springframework.http.HttpStatus;
	import org.springframework.http.ResponseEntity;
	import org.springframework.test.context.ActiveProfiles;
	import org.springframework.test.context.ContextConfiguration;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

	import com.developer.carsCatalog.CarsCatalogApplication;
	import com.developer.carsCatalog.controllers.CarsController;
	import com.developer.carsCatalog.entities.Cars;
	import com.developer.carsCatalog.entities.Make;
	import com.developer.carsCatalog.entities.Owner;
	import com.developer.carsCatalog.repositories.CarsRepository;

	@SpringBootTest
	@ActiveProfiles("test")
	@ContextConfiguration(classes = CarsCatalogApplication.class)
	public class CarsControllerIntegrationTest {

	    @Autowired
	    CarsController carsController;

	    @Mock
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

	        Owner owner1 = new Owner(1L, "Alice", 30);
	        Owner owner2 = new Owner(2L, "Bob", 40);

	        List<Owner> owners1 = new ArrayList<>();
	        owners1.add(owner1);
	        owners1.add(owner2);

	        List<Owner> owners2 = new ArrayList<>();
	        owners2.add(owner2);

	        car1 = new Cars(1L, "Corolla", 2020, makeToyota, owners1);
	        car2 = new Cars(2L, "Fit", 2019, makeHonda, owners2);
	        car3 = new Cars(3L, "Punto", 2018, makeFiat, new ArrayList<>());

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

	
	





		
		
		
		
	
	
	
	


