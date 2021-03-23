package com.example.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"loans","reservations"})
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(unique = true)
	private String name;
	private String password;
	
	private Integer numberLoaned;
	
	private Integer numberReservated;
	
	@OneToMany(mappedBy="user")
	private List<Loan> loans;
	
	@OneToMany(mappedBy="user")
	private List<Reservation> reservations;

	public User() {
		super();
	}

	public User(Integer id, String name, String password) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

	public List<Loan> getLoans() {
		return loans;
	}

	public void setLoans(List<Loan> loans) {
		this.loans = loans;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public Integer getNumberLoaned() {
		return numberLoaned;
	}

	public void setNumberLoaned(Integer numberLoaned) {
		this.numberLoaned = numberLoaned;
	}

	public Integer getNumberReservated() {
		return numberReservated;
	}

	public void setNumberReservated(Integer numberReservated) {
		this.numberReservated = numberReservated;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + "%hidden-for-safety%" + "]";
	}

}
