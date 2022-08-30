package com.packt.cardatabase.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "cars")
public interface CarRepository extends CrudRepository<Car, Long> {

	List<Car> findByColor(String color);

	List<Car> findByReleasedAt(int year);

	List<Car> findByColorAndReleasedAt(String color, int year);
}