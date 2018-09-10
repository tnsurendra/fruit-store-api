package com.cybercom.fruitstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cybercom.fruitstore.model.FruitObject;


/**
 * The Interface FruitRepository.
 */
@Repository
public interface FruitRepository extends JpaRepository<FruitObject, Integer> {

	/**
	 * Gets the fruit by name.
	 *
	 * @param name
	 *            the name
	 * @return the fruit by name
	 */
	@Query("SELECT f FROM FruitObject f WHERE LOWER(f.name) = LOWER(:name)")
	public FruitObject getFruitByName(String name);
}