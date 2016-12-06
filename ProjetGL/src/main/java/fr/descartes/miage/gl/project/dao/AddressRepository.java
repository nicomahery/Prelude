package fr.descartes.miage.gl.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.descartes.miage.gl.project.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{
	
}
