package com.packt.cardatabase.web;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.packt.cardatabase.domain.Owner;
import com.packt.cardatabase.domain.OwnerRepository;

@RestController
public class OwnerController {
	@Autowired
	private OwnerRepository ownerRepository;

	@RequestMapping("/owners")
	public Iterable<Owner> getOwners() {
		return ownerRepository.findAll();
	}
}