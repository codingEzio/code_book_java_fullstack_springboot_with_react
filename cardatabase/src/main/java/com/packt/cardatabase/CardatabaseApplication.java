package com.packt.cardatabase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;

import org.springframework.beans.factory.annotation.Autowired;

import com.packt.cardatabase.domain.Car;
import com.packt.cardatabase.domain.CarRepository;
import com.packt.cardatabase.domain.Owner;
import com.packt.cardatabase.domain.OwnerRepository;
import com.packt.cardatabase.domain.User;
import com.packt.cardatabase.domain.UserRepository;

import java.util.Arrays;

@SpringBootApplication
public class CardatabaseApplication implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(
			CardatabaseApplication.class
	);

	@Autowired
	private CarRepository carRepository;

	@Autowired
	private OwnerRepository ownerRepository;

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(CardatabaseApplication.class, args);

		logger.info("Application started!");
	}

	@Override
	public void run(String... args) throws Exception {
		Owner ownerAlex = new Owner("Alex", "Cameron");
		Owner ownerMosh = new Owner("Mosh", "Annie");

		ownerRepository.saveAll(Arrays.asList(ownerAlex, ownerMosh));

		carRepository.save(new Car("Ford", "Mustang", "Red", "ADF-1121", 59000, 2021, ownerAlex));
		carRepository.save(new Car("Ford", "Future", "White", "BEA-9221", 39312, 2020, ownerAlex));
		carRepository.save(new Car("Geely", "Redemption", "Red", "PAC-1241", 12553, 2021, ownerMosh));
		carRepository.save(new Car("Tesla", "Onward", "White", "POI-1422", 37461, 2019, ownerAlex));
		carRepository.save(new Car("Eurus", "Eastwind", "Green", "MJK-3214", 41422, 2020, ownerAlex));
		carRepository.save(new Car("Olive", "Anniversary", "White", "ERQ-2411", 15132, 2022, ownerMosh));
		carRepository.save(new Car("Future", "TotalEV", "Black", "JOK-1242", 64235, 2020, ownerMosh));

		// The passwords below are the same as the username (for the login process)
		// I went to https://www.bcryptcalculator.com/encode to encode the credentials
		userRepository.save(new User(
				"user",
				"$2a$10$nHZ.SGqmOMxIUbtob.THHOJwru.FLbfCNmTQUuq63ZeH6cgPTxeJe",
				"USER"
		));
		userRepository.save(new User(
				"admin",
				"$2a$10$X3T9r0OqC0sYyShi0f3qnuDUcYVkefU0hw6dyQAqKGtr0//HwBzX2",
				"ADMIN"
		));
	}
}