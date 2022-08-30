package com.packt.cardatabase.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.repository.query.Param;

@RepositoryRestResource(path = "cars")
public interface CarRepository extends CrudRepository<Car, Long> {

	List<Car> findByColor(@Param("color") String color);

	List<Car> findByReleasedAt(@Param("year") int year);

	List<Car> findByColorAndReleasedAt(@Param("color") String color, @Param("year") int year);
}