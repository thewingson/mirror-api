package kz.man.mirrorapi.publisher;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kz.man.mirrorapi.config.properties.MQTTProperties;
import kz.man.mirrorapi.pojo.Sensor;
import org.apache.log4j.Logger;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MQTTPublishClient {

    private static final Logger logger = Logger.getLogger(MQTTPublishClient.class);

    //http://www.hivemq.com/blog/mqtt-essentials-part-6-mqtt-quality-of-service-levels
    @Autowired
    private MQTTProperties mqttProperties;
    @Autowired
    private ObjectMapper objectMapper;

    public void send(Sensor sensor) {
        MemoryPersistence persistence = new MemoryPersistence();
        MqttClient sampleClient = null;

        try {

            sampleClient = new MqttClient("tcp://" + mqttProperties.getUrl() + ":" + mqttProperties.getPort(), mqttProperties.getClientPubId(), persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            //http://www.hivemq.com/blog/mqtt-essentials-part-7-persistent-session-queuing-messages
            connOpts.setCleanSession(true);

            if (mqttProperties.getUseCredential()) {
                connOpts.setUserName(mqttProperties.getUsername());
                connOpts.setPassword(mqttProperties.getPassword().toCharArray());
            }

            sampleClient.connect(connOpts);
            logger.info("send(): Publishing message: " + objectMapper.writeValueAsString(sensor) + " to topic: " + sensor.getTopic());
            MqttMessage message = new MqttMessage(objectMapper.writeValueAsBytes(sensor));
            message.setQos(Integer.parseInt(mqttProperties.getQos()));
//            http://www.hivemq.com/blog/mqtt-essentials-part-8-retained-messages
            message.setRetained(Boolean.FALSE);

            sampleClient.publish(sensor.getTopic(), message);
            sampleClient.disconnect();
        } catch (MqttException e) {
            logger.info("Error on send with MqttClient...");
        } catch (JsonProcessingException e) {
            logger.info("Error on parsing to json...");
        }
    }
}