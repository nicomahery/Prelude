package fr.descartes.miage.gl.project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.descartes.miage.gl.project.entities.Address;
import fr.descartes.miage.gl.project.entities.Advertisement;
import fr.descartes.miage.gl.project.entities.Category;
import fr.descartes.miage.gl.project.entities.User;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Long>{
	public List<Advertisement> findByOwner(User ownerId);
	public List<Advertisement> findByCategory(Category catId);
	
	@Query("select u from Advertisement u inner join u.address ar where ar.state = :adId")
	public List<Advertisement> findByAddress(Address adId);
}
