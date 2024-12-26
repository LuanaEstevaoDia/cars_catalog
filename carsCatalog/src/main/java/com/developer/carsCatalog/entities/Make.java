package com.developer.carsCatalog.entities;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Make implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String cnpj;
	
	@OneToMany(mappedBy = "make")
	@JsonIgnore
	private List<Cars> cars;
	
	public Make() {
		super();
	}

	public Make(Long id, String name, String cnpj, List<Cars> cars) {
		super();
		this.id = id;
		this.name = name;
		this.cnpj = cnpj;
		this.cars = cars;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public List<Cars> getCars() {
		return cars;
	}

	public void setCars(List<Cars> cars) {
		this.cars = cars;
	}
	
	

}
