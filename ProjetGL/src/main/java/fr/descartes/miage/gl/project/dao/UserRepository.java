package fr.descartes.miage.gl.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.descartes.miage.gl.project.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
