package com.packt.cardatabase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;

import org.springframework.beans.factory.annotation.Autowired;

import com.packt.cardatabase.domain.Car;
import com.packt.cardatabase.domain.CarRepository;

@SpringBootApplication
public class CardatabaseApplication implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(
			CardatabaseApplication.class
	);

	@Autowired
	private CarRepository carRepository;

	public static void main(String[] args) {
		SpringApplication.run(CardatabaseApplication.class, args);

		logger.info("Application started!");
	}

	@Override
	public void run(String... args) throws Exception {
		carRepository.save(new Car("Ford", "Mustang", "Red", "ADF-1121", 59000, 2021));
		carRepository.save(new Car("Ford", "Future", "White", "BEA-9221", 39312, 2020));
		carRepository.save(new Car("Geely", "Redemption", "Red", "PAC-1241", 12553, 2021));
		carRepository.save(new Car("Tesla", "Onward", "White", "POI-1422", 37461, 2019));
		carRepository.save(new Car("Eurus", "Eastwind", "Green", "MJK-3214", 41422, 2020));
		carRepository.save(new Car("Olive", "Anniversary", "White", "ERQ-2411", 15132, 2022));
		carRepository.save(new Car("Future", "TotalEV", "Black", "JOK-1242", 64235, 2020));

		for (Car car: carRepository.findByColor("White")) {
			logger.info("White Cars -> " + car.getBrand() + " " + car.getModel());
		}

		for (Car car: carRepository.findByReleasedAt(2020)) {
			logger.info("Released at 2020 -> " + car.getBrand() + " " + car.getModel());
		}

		for (Car car: carRepository.findByColorAndReleasedAt("White", 2020)) {
			logger.warn("White and 2020 -> " + car.getBrand() + " " + car.getModel());
		}
	}
}