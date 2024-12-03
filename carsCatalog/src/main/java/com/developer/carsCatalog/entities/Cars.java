package com.developer.carsCatalog.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Cars {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String manufacturer;
	private String model;
	private Integer years;
	
	
	
	
	
	public Cars() {
		super();
	}



	public Cars(Long id, String manufacturer, String model, Integer years) {
		super();
		this.id = id;
		this.manufacturer = manufacturer;
		this.model = model;
		this.years = years;
	
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getManufacturer() {
		return manufacturer;
	}



	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}



	public String getModel() {
		return model;
	}



	public void setModel(String model) {
		this.model = model;
	}



	public Integer getYears() {
		return years;
	}



	public void setYears(Integer years) {
		this.years = years;
	}
	
	
	
	
	

}
