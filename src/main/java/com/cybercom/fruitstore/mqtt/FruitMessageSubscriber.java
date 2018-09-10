package com.cybercom.fruitstore.mqtt;

import javax.transaction.Transactional;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cybercom.fruitstore.exception.FruitException;
import com.cybercom.fruitstore.model.FruitObject;
import com.cybercom.fruitstore.services.FruitService;


/**
 * The Class FruitMessageSubscriber.
 */
@Component
@Transactional
public class FruitMessageSubscriber implements MqttCallback {

	/** The fruit service. */
	@Autowired
	FruitService fruitService;

	/** The Constant mqttUrl. */
	static final String mqttUrl = "tcp://localhost:1883";

	/** The Constant mqttTopic. */
	static final String mqttTopic = "new/fruit";

	/** The mqtt client. */
	private MqttClient mqttClient;

	/** The connection options. */
	private MqttConnectOptions connectionOptions = null;

	/**
	 * Instantiates a new fruit message subscriber.
	 *
	 * @throws MqttException
	 *             the mqtt exception
	 */
	public FruitMessageSubscriber() throws MqttException {

		this.mqttClient = new MqttClient(mqttUrl, MqttClient.generateClientId(), new MemoryPersistence());
		this.connectionOptions = new MqttConnectOptions();
		this.connectionOptions.setCleanSession(true);
		this.mqttClient.connect(this.connectionOptions);
		this.mqttClient.setCallback(this);
		this.mqttClient.subscribe(mqttTopic);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.paho.client.mqttv3.MqttCallback#connectionLost(java.lang.
	 * Throwable)
	 */
	@Override
	public void connectionLost(Throwable cause) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.paho.client.mqttv3.MqttCallback#messageArrived(java.lang.String,
	 * org.eclipse.paho.client.mqttv3.MqttMessage)
	 */
	@Override
	public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
		// would be nice to save the fruits..

		processQueItem(new String(mqttMessage.getPayload()));

	}

	/**
	 * Process que item.
	 *
	 * @param payLoad
	 *            the pay load
	 * @throws Exception
	 *             the exception
	 */
	private void processQueItem(String payLoad) throws Exception {
		try {
			FruitObject fruit = convertMessageToFruit(payLoad);
			FruitObject fruitObject = fruitService.getFruitByName(fruit.getName());
			if (null == fruitObject) {
				throw new FruitException(fruit.getName() + " fruit not available");
			}
			fruit.setAvailableStock(fruitObject.getAvailableStock() + 1);
			fruit.setId(fruitObject.getId());
			fruit.setPrice(fruitObject.getPrice());
			fruitService.saveFruit(fruit);
		} catch (Exception e) {

			throw new FruitException("Unable to process que message:" + payLoad);
		}

	}

	/**
	 * Convert message to fruit.
	 *
	 * @param payLoad
	 *            the pay load
	 * @return the fruit object
	 * @throws Exception
	 *             the exception
	 */
	private FruitObject convertMessageToFruit(String payLoad) throws Exception {
		FruitObject fruitObject = null;
		try {
			JSONObject fruit = new JSONObject(payLoad);

			String name, type = null;

			if (fruit.has("name")) {
				name = fruit.getString("name");

			} else
				throw new FruitException("Missing fruit name");

			if (fruit.has("type")) {
				type = fruit.getString("type");

			} else
				throw new FruitException("Missing fruit type");

			fruitObject = new FruitObject(type, name);

		} catch (JSONException jsonException) {
			throw new FruitException("Invalid JSON format in message:" + payLoad);
		}
		return fruitObject;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.paho.client.mqttv3.MqttCallback#deliveryComplete(org.eclipse.paho
	 * .client.mqttv3.IMqttDeliveryToken)
	 */
	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {

	}

}
