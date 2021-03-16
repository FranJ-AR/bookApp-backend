package com.example.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@Deprecated
public class Worker {
	
	@Id
	private Integer id;
	private String name;
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	//@JoinColumn(name = "WORKS_IN_BUILDING")
	private Building worksIn;
	
	
	public Worker() {
		super();
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


	public Building getWorksIn() {
		return worksIn;
	}


	public void setWorksIn(Building worksIn) {
		this.worksIn = worksIn;
	}
	
	

}
