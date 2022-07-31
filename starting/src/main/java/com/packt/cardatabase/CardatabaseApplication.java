package com.packt.cardatabase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;

import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Autowired;

import com.packt.cardatabase.domain.Car;
import com.packt.cardatabase.domain.CarRepository;
import com.packt.cardatabase.domain.Owner;
import com.packt.cardatabase.domain.OwnerRepository;

@SpringBootApplication
public class CardatabaseApplication {

	@Autowired
	private CarRepository carRepository;

	@Autowired
	private OwnerRepository ownerRepository;

	private static final Logger logger = LoggerFactory.getLogger(CardatabaseApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(
				CardatabaseApplication.class,
				args
		);
		logger.info("Slf4j says hello!");
	}

	// Preloading demo data
	@Bean
	CommandLineRunner runner() {
		return args -> {
			Owner owner1 = new Owner("John", "Doe");
			Owner owner2 = new Owner("Dua", "Lipa");
			ownerRepository.save(owner1);
			ownerRepository.save(owner2);

			carRepository.save(new Car("Ford", "Mustang", "Red",
					"ADF-1121", 2017, 59000, owner1));
			carRepository.save(new Car("Nissan", "Leaf", "White",
					"SSJ-3002", 2014, 29000, owner1));
			carRepository.save(new Car("Toyota", "Prius", "Silver",
					"KKO-0212", 2018, 39000, owner2));
		};
	}

}