package fr.descartes.miage.gl.project;

import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fr.descartes.miage.gl.project.dao.AddressRepository;
import fr.descartes.miage.gl.project.dao.AdvertisementRepository;
import fr.descartes.miage.gl.project.dao.CategoryRepository;
import fr.descartes.miage.gl.project.dao.PhotoRepository;
import fr.descartes.miage.gl.project.dao.UserRepository;
import fr.descartes.miage.gl.project.entities.Address;
import fr.descartes.miage.gl.project.entities.Advertisement;
import fr.descartes.miage.gl.project.entities.Category;
import fr.descartes.miage.gl.project.entities.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjetGlApplicationTests {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private AdvertisementRepository advertisementRepository;
	@Autowired
	private PhotoRepository photoRepository;
	
	@Test
	public void subscribeTest() {
		int addressCount = addressRepository.findAll().size();
		int userCount = userRepository.findAll().size();
		Address a = this.createAddress();
		User u = this.createUser();
		User uu = userRepository.save(u);
		int addressCount2 = addressRepository.findAll().size();
		int userCount2 = userRepository.findAll().size();
		userRepository.delete(uu);
		addressRepository.delete(uu.getAddress());
		assertTrue(addressCount2 == (addressCount+1) && userCount2 == (userCount+1));
	}
	
	@Test
	public void insertCategoryTest() {
		int categoryCount = categoryRepository.findAll().size();
		Category c = this.createCategory();
		Category cc = categoryRepository.save(c);
		int categoryCount2 = categoryRepository.findAll().size();
		categoryRepository.delete(cc);
		assertTrue(categoryCount2 == (categoryCount+1));
	}
	
	@Test
	public void insertAddressTest() {
		int addressCount = addressRepository.findAll().size();
		Address a = this.createAddress();
		Address aa = addressRepository.save(a);
		int addressCount2 = addressRepository.findAll().size();
		addressRepository.delete(aa);
		assertTrue(addressCount2 == (addressCount+1));
	}
	
	@Test
	public void insertAdvertisementTest() {
		int advertisementCount = advertisementRepository.findAll().size();
		Advertisement a = this.createAdvertisement();
		addressRepository.save(a.getAddress());
		categoryRepository.save(a.getCategory());
		userRepository.save(a.getOwner());
		Advertisement aa = advertisementRepository.save(a);
		int advertisementCount2 = advertisementRepository.findAll().size();
		advertisementRepository.delete(aa);
		categoryRepository.delete(aa.getCategory());
		addressRepository.delete(aa.getAddress());
		userRepository.delete(aa.getOwner());
		assertTrue(advertisementCount2 == (advertisementCount+1));
	}
	
	public Address createAddress(){
		String street = "36 quai des orfèvres";
		String city = "Paris";
		String country = "France";
		String zip = "75001";
		String state = "Ile-de-France";
		Address a = new Address();
		a.setCity(city);
		a.setCountry(country);
		a.setState(state);
		a.setStreet(street);
		a.setZip(zip);
		return a;
	}
	
	public User createUser(){

		String username = "test";
		String firstName = "first";
		String lastName = "last";
		String mailAddress = "test@test.fr";
		String password = "test0";
		String phoneNumber = "0123456";
		User u = new User();
		Address a = this.createAddress();
		u.setFirstName(firstName);
		u.setLastName(lastName);
		u.setMailAddress(mailAddress);
		u.setPassword(password);
		u.setPhoneNumber(phoneNumber);
		u.setUsername(username);
		u.setAddress(a);
		return u;
	}
	
	public Category createCategory(){
		String name = "testName";
		Category c = new Category();
		c.setName(name);
		return c;
	}
	
	public Advertisement createAdvertisement(){
		Timestamp date = new Timestamp(System.currentTimeMillis());
		String description = "Test test test test test test.";
		Boolean sold = false;
		String title = "Test TEST";
		User owner = this.createUser();
		Address address = this.createAddress();
		Advertisement a = new Advertisement();
		Category category = this.createCategory();
		a.setAddress(address);
		a.setCategory(category);
		a.setDate(date);
		a.setDescription(description);
		a.setOwner(owner);
		a.setSold(sold);
		a.setTitle(title);
		return a;
	}
}
