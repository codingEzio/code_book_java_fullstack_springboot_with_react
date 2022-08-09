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
import com.packt.cardatabase.domain.User;
import com.packt.cardatabase.domain.UserRepository;

@SpringBootApplication
public class CardatabaseApplication {

	@Autowired
	private CarRepository carRepository;

	@Autowired
	private OwnerRepository ownerRepository;

	@Autowired
	private UserRepository userRepository;

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
			Owner owner3 = new Owner("Oscar", "Wild");
			Owner owner4 = new Owner("Elizabeth", "Olsen");
			ownerRepository.save(owner1);
			ownerRepository.save(owner2);
			ownerRepository.save(owner3);
			ownerRepository.save(owner4);

			carRepository.save(new Car("Ford", "Mustang", "Red",
					"ADF-1121", 2017, 59000, owner1));
			carRepository.save(new Car("Nissan", "Leaf", "White",
					"SSJ-3002", 2014, 29000, owner2));
			carRepository.save(new Car("Toyota", "Prius", "Silver",
					"KKO-0212", 2018, 39000, owner3));
			carRepository.save(new Car("Tesla", "Young", "Black",
					"TSA-2021", 2021, 49000, owner4));
			carRepository.save(new Car("GM", "Ambition", "Yellow",
					"GMM-1221", 2020, 14000, owner1));
			carRepository.save(new Car("BYD", "Prime", "Green",
					"BYD-0441", 2014, 55000, owner3));

			userRepository.save(new User(
					"test",
					"$2a$10$puSktOaZYELsHQESAvQtHexz/JpJuGjqa4fsxlHFxTv3NpxThut/G",
					"USER"
			));
			userRepository.save(new User(
					"user",
					"$2a$10$k5Aokk0jsJNwDWW.sYv56.9W6YXhYCOTyDGqeTQHeAECz.l8PnT92",
					"USER"
			));
			userRepository.save(new User(
					"admin",
					"$2a$10$gB2/X.J5ELwpg85nkQ/CEe5CSjWWE51VCgQN0CjLilV/9GQZ4bTie",
					"ADMIN"
			));
		};
	}

}