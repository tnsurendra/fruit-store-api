package com.cybercom.fruitstore.mqtt;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 * The Class FruitsMessagePublisher.
 */
@Component

public class FruitsMessagePublisher {

	

	
	/** The connection options. */
	private MqttConnectOptions connectionOptions = null;
	
	/**
	 * Schedule task with fixed delay.
	 *
	 * @throws MqttException the mqtt exception
	 */
	@Scheduled(fixedDelay = 60000)
	public void scheduleTaskWithFixedDelay() throws MqttException {
		
		
		Random rand = new Random();
		String mango="{ \"name\":\"Alphonso\", \"type\":\"Mango\" }";
		String banana="{ \"name\":\"Red Banana\", \"type\":\"Banana\" }";
		String apple="{ \"name\":\"Green Apple\",  \"type\":\"Apple\"}";
		String pomo="{ \"name\":\"Hybrid Pomegranate\",  \"type\":\"Pomegranate\" }";
		
		String fruits[]= {mango,banana,apple,pomo};





        MqttClient client= new MqttClient(FruitMessageSubscriber.mqttUrl, MqttClient.generateClientId(),new MemoryPersistence());
        this.connectionOptions = new MqttConnectOptions();
		this.connectionOptions.setCleanSession(true);
		client.connect(this.connectionOptions);
		MqttMessage message = new MqttMessage();
		message.setPayload(fruits[rand.nextInt(4)].getBytes());
		client.publish(FruitMessageSubscriber.mqttTopic, message);
		client.disconnect();


	}
	


}
