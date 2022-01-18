package com.btkAkademi.rentACar.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "credit_cards")
public class CreditCard {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "first_name_and_last_name")
	private String firstNameAndLastName;
	
	@Column(name = "expiration_date")
	private String expirationDate;
	
	@Column(name = "ccv")
	private String cCv;
	
	@Column(name = "card_number")
	private String cardNumber;
	
	@Column(name = "is_deleted")
	private boolean isDeleted;

}
