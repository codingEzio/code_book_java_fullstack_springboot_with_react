package com.packt.cardatabase.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Car {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	private String brand, model, color, registerNumber;
	private int price, releasedAt;

	public Car () {
	}

	public Car (String brand, String model, String color, String registerNumber,
	            int price, int releasedAt) {
		this.brand = brand;
		this.model = model;
		this.color = color;
		this.registerNumber = registerNumber;
		this.price = price;
		this.releasedAt = releasedAt;
	}
}