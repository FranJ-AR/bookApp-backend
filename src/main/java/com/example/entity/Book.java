package com.example.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@JsonIgnoreProperties({"loans","reservations"})
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	
	//@JsonBackReference
	@ManyToOne
	private Category category;
	
	//@JsonBackReference
	@ManyToOne
	private Subcategory subcategory;
	
	//@JsonBackReference
	@ManyToOne
	private Author author;
	
	private String imageUrl;
	
	private Integer numberCopies;
	
	private Integer numberLoaned;
	
	private Integer numberReservated;
	
	private String description;
	
	
	@OneToMany(mappedBy="book")
	private List<Loan> loans;
	
	@OneToMany(mappedBy="book")
	private List<Reservation> reservations;

	public Book() {
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Subcategory getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(Subcategory subcategory) {
		this.subcategory = subcategory;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Integer constant() {
		
		return 3;
	}
	
	public Integer getNumberCopies() {
		return numberCopies;
	}

	public void setNumberCopies(Integer numberCopies) {
		this.numberCopies = numberCopies;
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

	@JsonProperty("canBeLoaned")
	public boolean canBeLoaned() {
		
		return  getNumberLoaned() < getNumberCopies();
	}
	
	
	

}
