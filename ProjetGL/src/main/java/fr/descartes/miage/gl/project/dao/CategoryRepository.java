package fr.descartes.miage.gl.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.descartes.miage.gl.project.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
	public Category findByName(String nomCat);
}
