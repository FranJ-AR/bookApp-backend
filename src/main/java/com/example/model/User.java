package com.example.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@JsonIgnoreProperties({"id","password","loans","reservations"})
@Table(name = "user_app")
public class User {
	
	public static final Integer MAX_LOANS = 6;
	public static final Integer MAX_RESERVATIONS = 6;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String name;
	private String password;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Loan> loans;
	
	@OneToMany(mappedBy="user", fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Reservation> reservations;

	public User() {
		super();
	}

	public User(Long id, String name, String password) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
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

	@JsonProperty("numberLoaned")
	public Integer getNumberLoaned() {
		return getLoans().size();
	}

	@JsonProperty("numberReservated")
	public Integer getNumberReservated() {
		return getReservations().size();
	}
	
	@JsonProperty("canLoan")
	public boolean canLoan() {
		
		return getNumberLoaned() < MAX_LOANS;
		
	}
	
	@JsonProperty("canReservate")
	public boolean canReservate() {
		
		return getNumberReservated() < MAX_RESERVATIONS;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + "%hidden-for-safety%" + "]";
	}

}
