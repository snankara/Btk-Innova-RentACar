package com.btkAkademi.rentACar.entities.concretes;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "additionals")
public class Additional {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "additional_name")
	private String additionalName;
	
	@Column(name = "additional_amount")
	private double additionalAmount;
	
	@Column(name = "is_deleted")
	private boolean isDeleted;
	
	@OneToMany(mappedBy = "additional")
	private List<AdditionalItem> additionalItems;
	
}
