package com.cybercom.fruitstore.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.cybercom.fruitstore.model.FruitObject;
import com.cybercom.fruitstore.services.FruitService;

// TODO: Auto-generated Javadoc
/**
 * The Class FruitStoreTests.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class FruitStoreTests {

	/** The port. */
	@LocalServerPort
	private int port;

	/** The fruit service. */
	@MockBean
	private FruitService fruitService;

	/** The rest template. */
	@Autowired
	TestRestTemplate restTemplate = new TestRestTemplate();

	/** The headers. */
	HttpHeaders headers = new HttpHeaders();

	/**
	 * Test get fruit valid input.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testGetFruitValidInput() throws Exception {
		FruitObject fruitObj = new FruitObject();
		fruitObj.setName("Green Banana");
		fruitObj.setType("Banana");
		fruitObj.setPrice(37.00);

		fruitObj.setAvailableStock(10.0);
		when(fruitService.getFruitByName("Green Banana")).thenReturn(fruitObj);
		ResponseEntity<FruitObject> response = restTemplate.exchange(
				createURLWithPort("/fruitstore/getFruit/Green Banana/1"), HttpMethod.GET, null, FruitObject.class);
		assertEquals(new Double(9), response.getBody().getAvailableStock());
	}

	/**
	 * Test get fruit invaid quntity.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testGetFruitInvaidQuntity() throws Exception {
		FruitObject fruitObj = new FruitObject();
		fruitObj.setName("Green Banana");
		fruitObj.setType("Banana");
		fruitObj.setPrice(37.00);
		fruitObj.setAvailableStock(10.0);
		when(fruitService.getFruitByName("Green Banana")).thenReturn(fruitObj);
		ResponseEntity<FruitObject> response = restTemplate.exchange(
				createURLWithPort("/fruitstore/getFruit/Green Banana/22"), HttpMethod.GET, null, FruitObject.class);

		System.out.println("respons****************" + response);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
	}

	/**
	 * Test save fruits valid input.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testSaveFruitsValidInput() throws Exception {

		FruitObject fruitObj = new FruitObject();
		fruitObj.setName("Small Apple");
		fruitObj.setType("Apple");
		fruitObj.setPrice(17.00);

		when(fruitService.saveFruit(Mockito.any(FruitObject.class))).thenReturn(fruitObj);
		ResponseEntity<FruitObject> response = restTemplate.exchange(createURLWithPort("/fruitstore/storeFruit"),
				HttpMethod.POST, new HttpEntity<FruitObject>(fruitObj), FruitObject.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Small Apple", response.getBody().getName());
	}

	/**
	 * Test save fruits duplicate fruit name.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testSaveFruitsDuplicateFruitName() throws Exception {

		FruitObject fruitObj = new FruitObject();
		fruitObj.setName("Small Apple");
		fruitObj.setType("Apple");
		fruitObj.setPrice(17.00);

		when(fruitService.saveFruit(Mockito.any(FruitObject.class))).thenReturn(fruitObj);
		FruitObject duplicateFruitObj = new FruitObject();
		duplicateFruitObj.setName("Small Apple");
		duplicateFruitObj.setType("Apple");
		duplicateFruitObj.setPrice(17.00);
		ResponseEntity<FruitObject> response = restTemplate.exchange(createURLWithPort("/fruitstore/storeFruit"),
				HttpMethod.POST, new HttpEntity<FruitObject>(duplicateFruitObj), FruitObject.class);
		assertEquals(HttpStatus.EXPECTATION_FAILED, response.getStatusCode().EXPECTATION_FAILED);

	}

	/**
	 * Test update fruit valid input.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testUpdateFruitValidInput() throws Exception {
		FruitObject fruitObj = new FruitObject();
		fruitObj.setName("Cuntry Pomegranate");
		fruitObj.setType("Pomegranate");
		fruitObj.setPrice(15.00);
		fruitObj.setId(1l);

		when(fruitService.saveFruit(Mockito.any(FruitObject.class))).thenReturn(fruitObj);
		ResponseEntity<FruitObject> response = restTemplate.exchange(createURLWithPort("/fruitstore/updateFruit"),
				HttpMethod.PUT, new HttpEntity<FruitObject>(fruitObj), FruitObject.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	/**
	 * Test update fruit in valid id.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testUpdateFruitInValidId() throws Exception {
		FruitObject fruitObj = new FruitObject();
		fruitObj.setName("Cuntry Pomegranate");
		fruitObj.setType("Pomegranate");
		fruitObj.setPrice(15.00);

		when(fruitService.saveFruit(Mockito.any(FruitObject.class))).thenReturn(fruitObj);
		ResponseEntity<FruitObject> response = restTemplate.exchange(createURLWithPort("/fruitstore/updateFruit"),
				HttpMethod.PUT, new HttpEntity<FruitObject>(fruitObj), FruitObject.class);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
	}

	/**
	 * Test get all fruits valid input.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testGetAllFruitsValidInput() throws Exception {
		List<FruitObject> list = new ArrayList<FruitObject>();
		FruitObject fruitObj = new FruitObject();
		fruitObj.setName("Cuntry Pomegranate");
		fruitObj.setType("Pomegranate");
		fruitObj.setPrice(15.00);
		list.add(fruitObj);
		when(fruitService.listAllFruits()).thenReturn(list);

		ResponseEntity<List> response = restTemplate.exchange(createURLWithPort("/fruitstore/getAllFruits"),
				HttpMethod.GET, null, List.class);
		assertEquals(1, response.getBody().size());

	}

	/**
	 * Creates the URL with port.
	 *
	 * @param uri
	 *            the uri
	 * @return the string
	 */
	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

}
