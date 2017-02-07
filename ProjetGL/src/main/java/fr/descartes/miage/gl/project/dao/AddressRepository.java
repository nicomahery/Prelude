package fr.descartes.miage.gl.project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.descartes.miage.gl.project.entities.Address;
import fr.descartes.miage.gl.project.entities.Advertisement;
import fr.descartes.miage.gl.project.entities.User;

public interface AddressRepository extends JpaRepository<Address, Long>{
	public List<Advertisement> findByState(String state);
}
