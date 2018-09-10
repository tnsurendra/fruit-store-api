FruitStore

This project is a simple fruit store application to view/add/update fruits .



Getting Started

This is sample application developed using spring-boot,H2 in-memory DB.
A MQTT Publisher and Subscriber has been used for sending and receiving the message through message broker(mosquitto).


Prerequisites

Install a MQTT Broker, for example [Mosquitto](https://mosquitto.org/

Installing

1- Run the MQTT broker

For example using Mosquitto on OSX:

/usr/local/sbin/mosquitto

2- Build the project with Apache Maven or eclipse:

This project is a simple spring-boot application to do some operations on fruits.

Run the below maven command to build the project.

$ mvn clean install

3- Run the project

A runnable jar will be generated in target folder of project.

Run the below maven command to spin-up the application.

$ mvn spring-boot:run


Swagger:

you can access swagger UI from below url:

http://localhost:8080/swagger-ui.html#/Friuts_API



Detailed:

This is simple project to do some operations on fruits through REST API.
In addition to that ,There is an MQTT publish/subscriber messaging system to send/receive fruit messages.
As the type of fruits continuously comes available stock at fruit store.
 
Below are the  available REST APIS :


1./getAllFruits

This REST API allows users to view all available fruits at Fruit store.

2./storeFruit

This REST API allows users to create a new fruit in Fruit store.

3./updateFruit

This REST API allows users to update an existing fruit details such as available stock,name,price,type of fruit.

4./getFruit

This REST API allows users to get some quantity of fruits from available stock and updates the stock.


Testing:

I have included basic test cases in src/test folder.
In addition to that,You can also test REST APIS from post man.
Attached post-man collection(fruits_postman-collcetion.json) in src/test folder.You can directly import and test.


MQTT messaging system:

Basically MQTT has two parts.Publish and Subscribe.

Publisher will run as a scheduler job (every 1 minute) to continuously  send some random fruits as a JSON message.


Subscriber will receive the published JSON messages and check for name of the fruit, if founds correct,
 it will increase the available stock of that particular fruit.

Testing:

You can publish a message  through message broker(mosquitto) in the below format.

{"name":"<Name of fruit>", "type":"<Type of fruit>"}

Note:This values for name and type must be taken from an existing fruit.

Ex:{ "name":"Alphonso", "type":"Mango" }

MQTT DETAILS:
MQTT URL : tcp://localhost:1883
MQTT TOPIC : new/fruit




