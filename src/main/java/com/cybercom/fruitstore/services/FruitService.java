package com.cybercom.fruitstore.services;


import com.cybercom.fruitstore.model.FruitObject;


/**
 * The Interface FruitService.
 */
public interface FruitService {
    
    /**
     * List all fruits.
     *
     * @return the iterable
     */
    Iterable<FruitObject> listAllFruits();

    /**
     * Gets the fruit by id.
     *
     * @param id the id
     * @return the fruit by id
     */
    FruitObject getFruitById(Integer id);
    
    /**
     * Gets the fruit by name.
     *
     * @param name the name
     * @return the fruit by name
     */
    FruitObject getFruitByName(String name);

    /**
     * Save fruit.
     *
     * @param product the product
     * @return the fruit object
     */
    FruitObject saveFruit(FruitObject product);

    
}
