package fr.descartes.miage.gl.project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.descartes.miage.gl.project.entities.Advertisement;
import fr.descartes.miage.gl.project.entities.Photo;

public interface PhotoRepository extends JpaRepository<Photo, Long>{
	
	List<Photo> findByAdvertisement(Advertisement id);
}
