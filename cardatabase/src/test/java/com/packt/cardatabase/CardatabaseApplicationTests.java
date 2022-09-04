package com.packt.cardatabase;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.context.SpringBootTest;

import com.packt.cardatabase.web.CarController;

@SpringBootTest
class CardatabaseApplicationTests {

	@Autowired
	private CarController carController;

	@Test
	@DisplayName("First example test for Car Controller")
	void contextLoads() {
		assertThat(carController).isNotNull();
	}

}