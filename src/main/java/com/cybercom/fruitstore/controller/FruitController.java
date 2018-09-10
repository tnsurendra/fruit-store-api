package com.cybercom.fruitstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cybercom.fruitstore.exception.FruitException;
import com.cybercom.fruitstore.model.FruitObject;
import com.cybercom.fruitstore.services.FruitService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

// TODO: Auto-generated Javadoc
/**
 * The Class FruitController.
 *
 * @author Cybercom
 */
@Repository
@RestController
@RequestMapping(path = "/fruitstore")
@Api(value = "Fruit store", description = "Operations pertaining to fruits in store", tags = "Friuts API")
@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully operation on fruits"),
		@ApiResponse(code = 500, message = "Unbable to retrieve fruits") })

public class FruitController {

	/** The fruit service. */
	@Autowired
	FruitService fruitService;

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4279154482819675728L;

	/**
	 * Gets the all fruits.
	 *
	 * @return the all fruits
	 */
	@ApiOperation(value = "View list of available fruits", response = FruitObject.class)
	@RequestMapping(method = RequestMethod.GET, path = "/getAllFruits", produces = "application/json")
	public ResponseEntity<List<FruitObject>> getAllFruits() {

		List<FruitObject> allFruits = (List<FruitObject>) fruitService.listAllFruits();

		return new ResponseEntity(allFruits, HttpStatus.OK);
	}

	/**
	 * Store fruit.
	 *
	 * @param fruitObject
	 *            the fruit object
	 * @return the response entity
	 * @throws FruitException
	 *             the fruit exception
	 */
	@ApiOperation(value = "Store a new fruit", response = FruitObject.class)
	@RequestMapping(path = "/storeFruit", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<FruitObject> storeFruit(@RequestBody FruitObject fruitObject) throws FruitException {
		if (null != fruitObject && null == fruitObject.getName()) {
			throw new FruitException("Fruit name is required");
		}
		if (null == fruitObject.getPrice() || fruitObject.getPrice() <= 0) {
			throw new FruitException("Invalid price ");
		}
		if (null != fruitService.getFruitByName(fruitObject.getName())) {
			throw new FruitException("Fruit with name already exists");
		}
		fruitObject.setId(null);// Always it should be a new fruit
		FruitObject fruit = fruitService.saveFruit(fruitObject);
		return new ResponseEntity(fruit, HttpStatus.OK);

	}

	/**
	 * Update.
	 *
	 * @param fruitObject
	 *            the fruit object
	 * @return the response entity
	 * @throws FruitException
	 *             the fruit exception
	 */
	@ApiOperation(value = "Update Fruit details", response = FruitObject.class)
	@RequestMapping(path = "/updateFruit", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<FruitObject> update(@RequestBody FruitObject fruitObject) throws FruitException {

		if (null != fruitObject && (null == fruitObject.getName() || null == fruitObject.getId())) {
			throw new FruitException("Name and ID required");
		}
		if (null == fruitObject.getPrice() || fruitObject.getPrice() <= 0) {
			throw new FruitException("Invalid price ");
		}

		FruitObject fruit = fruitService.saveFruit(fruitObject);
		if (null == fruit) {
			throw new FruitException("Invalid Fruit Details Specified");
		}
		return new ResponseEntity(fruit, HttpStatus.OK);
	}

	/**
	 * Gets the fruit.
	 *
	 * @param name
	 *            the name
	 * @param quantity
	 *            the quantity
	 * @return the fruit
	 * @throws FruitException
	 *             the fruit exception
	 */
	@ApiOperation(value = "Request for specific fruit with quantity", response = FruitObject.class)
	@RequestMapping(method = RequestMethod.GET, path = "/getFruit/{name}/{quantity}", produces = "application/json")
	public ResponseEntity<FruitObject> getFruit(@PathVariable("name") String name,
			@PathVariable("quantity") Double quantity) throws FruitException {

		if (null == quantity || (null != quantity && quantity <= 0)) {
			throw new FruitException("Invalid qunatity  specified");
		}
		FruitObject fruitObject = fruitService.getFruitByName(name);
		if (null == fruitObject) {
			throw new FruitException("Invalid Fruit Details Specified");
		}
		if (quantity > fruitObject.getAvailableStock()) {
			throw new FruitException("Required quantity not available at store");
		}
		fruitObject.setAvailableStock(fruitObject.getAvailableStock() - quantity);
		fruitService.saveFruit(fruitObject);

		return new ResponseEntity<FruitObject>(fruitObject, HttpStatus.OK);
	}

}
