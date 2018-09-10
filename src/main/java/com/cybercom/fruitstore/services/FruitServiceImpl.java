package com.cybercom.fruitstore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cybercom.fruitstore.model.FruitObject;
import com.cybercom.fruitstore.repository.FruitRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class FruitServiceImpl.
 */
@Service
public class FruitServiceImpl implements FruitService {

	/** The Fruit repository. */
	private FruitRepository FruitRepository;

	/**
	 * Sets the fruit repository.
	 *
	 * @param FruitRepository
	 *            the new fruit repository
	 */
	@Autowired
	public void setFruitRepository(FruitRepository FruitRepository) {
		this.FruitRepository = FruitRepository;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cybercom.fruitstore.services.FruitService#getFruitById(java.lang.Integer)
	 */
	@Override
	public FruitObject getFruitById(Integer id) {

		return FruitRepository.findById(id).orElse(null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cybercom.fruitstore.services.FruitService#saveFruit(com.cybercom.
	 * fruitstore.model.FruitObject)
	 */
	@Override
	public FruitObject saveFruit(FruitObject Fruit) {

		return FruitRepository.save(Fruit);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cybercom.fruitstore.services.FruitService#listAllFruits()
	 */
	@Override
	public Iterable<FruitObject> listAllFruits() {
		// TODO Auto-generated method stub
		return FruitRepository.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cybercom.fruitstore.services.FruitService#getFruitByName(java.lang.
	 * String)
	 */
	@Override
	public FruitObject getFruitByName(String name) {

		return FruitRepository.getFruitByName(name);
	}

}
