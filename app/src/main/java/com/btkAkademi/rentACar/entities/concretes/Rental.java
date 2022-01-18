package com.btkAkademi.rentACar.entities.concretes;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rentals")
public class Rental {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "rent_date")
	private LocalDate rentDate;
	
	@Column(name = "return_date")
	private LocalDate returnDate;
		
	@Column(name = "rented_kilometer")
	private int rentedKilometer;
	
	@Column(name = "returned_kilometer")
	private int returnedKilometer;
	
	@Column(name = "is_deleted")
	private boolean isDeleted;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name = "car_id")
	private Car car;
	
	@ManyToOne
	@JoinColumn(name = "rented_city_id")
	private City rentedCity;
	
	@ManyToOne
	@JoinColumn(name = "returned_city_id")
	private City returnedCity;
	
	@OneToMany(mappedBy = "rental")
	private List<Additional> additionals;

}
