package com.cybercom.fruitstore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * The Class FruitObject.
 */
// fruit
@Entity
public class FruitObject {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	/** The type. */
	// Type
	private String type;

	/** The name. */
	// Name
	@Column(name = "name", unique = true)
	private String name;

	/** The price. */
	// Price
	@Column(name = "price")
	private Double price = 0.0;

	/** The available stock. */
	// AvailableStock In KG
	@Column(name = "availableStock")
	private Double availableStock = 0.0;

	/**
	 * Gets the available stock.
	 *
	 * @return the available stock
	 */
	public Double getAvailableStock() {
		return availableStock;
	}

	/**
	 * Sets the available stock.
	 *
	 * @param availableStock
	 *            the new available stock
	 */
	public void setAvailableStock(Double availableStock) {
		this.availableStock = availableStock;
	}

	/**
	 * Instantiates a new fruit object.
	 */
	public FruitObject() {

	}

	/**
	 * Instantiates a new fruit object.
	 *
	 * @param type
	 *            the type
	 * @param name
	 *            the name
	 */
	public FruitObject(String type, String name) {

		// set type
		setType(type);

		// set name
		setName(name);

	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type
	 *            the new type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the price.
	 *
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * Sets the price.
	 *
	 * @param price
	 *            the new price
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * Sets the name.
	 *
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "name:" + name + ",type:" + type + ",price:" + price + ",availableStock:" + availableStock;
	}

}