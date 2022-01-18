package com.btkAkademi.rentACar.entities.concretes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cars")
public class Car {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "daily_price")
	private double dailyPrice;
	
	@Column(name = "model_year")
	private int modelYear;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "kilometer")
	private int kilometer;
	
	@Column(name = "min_customer_age")
	private int minCustomerAge;
	
	@Column(name = "min_findex_score")
	private int minFindexScore;
	
	@Column(name = "rental_state")
	private boolean rentalState;

	@Column(name = "is_deleted")
	private boolean isDeleted;
	
	@OneToOne
	@JoinColumn(name = "class_type_id")
	private ClassType classType;
	
	@ManyToOne
	@JoinColumn(name = "brand_id") //brand tablosundaki id alanı ile ilişkili.
	private Brand brand;
	
	@ManyToOne
	@JoinColumn(name = "color_id") 
	private Color color;
	
	@OneToMany(mappedBy = "car")
	private List<CarMaintenance> carMaintenances;
	
	@OneToMany(mappedBy = "car")
	private List<Rental> rentals;
	
	@OneToMany(mappedBy = "car")
	private List<CarDamage> carDamages;
}
