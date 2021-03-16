package com.example.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Deprecated
public class Building {
	
	@Id
	private Integer id;
	
	private String name;
	
	private Integer price;
	
	

	public Building() {
		super();
	}



	public Building(String name) {
		super();
		this.name = name;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public Integer getPrice() {
		return price;
	}



	public void setPrice(Integer price) {
		this.price = price;
	}
	
	
	
	

}
