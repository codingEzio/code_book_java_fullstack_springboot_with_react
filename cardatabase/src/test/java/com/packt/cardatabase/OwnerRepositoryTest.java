package com.packt.cardatabase;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.junit.jupiter.api.Test;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.packt.cardatabase.domain.Owner;
import com.packt.cardatabase.domain.OwnerRepository;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@DataJpaTest
public class OwnerRepositoryTest {

	@Autowired
	private OwnerRepository ownerRepository;

	@Test
	void saveOwner() {
		ownerRepository.save(new Owner("John", "Smith"));
		assertThat(ownerRepository.findByFirstname("John").isPresent()).isTrue();
	}

	@Test
	void deleteOwners() {
		ownerRepository.save(new Owner("John", "Smith"));
		ownerRepository.deleteAll();

		assertThat(ownerRepository.count()).isEqualTo(0);
	}
}