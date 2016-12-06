package fr.descartes.miage.gl.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.descartes.miage.gl.project.entities.Photo;

public interface PhotoRepository extends JpaRepository<Photo, Long>{

}
