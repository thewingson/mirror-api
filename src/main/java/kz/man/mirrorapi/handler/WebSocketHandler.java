package kz.man.mirrorapi.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import kz.man.mirrorapi.bean.MqttMessage;
import kz.man.mirrorapi.pojo.Sensor;
import kz.man.mirrorapi.service.RoomService;
import kz.man.mirrorapi.service.WebSocketService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.handler.AbstractMessageHandler;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class WebSocketHandler extends AbstractMessageHandler {

    private static final Logger logger = Logger.getLogger(WebSocketHandler.class.getName());

    @Autowired
    private WebSocketService webSocketService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    protected void handleMessageInternal(Message<?> msg) throws Exception {
        MessageHeaders headers = msg.getHeaders();
        String topic = (String) headers.get("mqtt_topic");
        MqttMessage mqttMessage = new MqttMessage(topic, (String) msg.getPayload(), "handler", new Date());
        Sensor sensor = objectMapper.readValue(mqttMessage.getContent().getBytes(), Sensor.class);
//        System.out.println(sensor);

        roomService.changeState(sensor);

//        Gson gson = new Gson();
//        try {
//            logger.info("handler(): sending " + mqttMessage + " from MQTT to WebSocket...");
//            webSocketService.sendMessage("/user/topic/greetings", gson.toJson(mqttMessage));
//        } catch (Exception e) {
//            logger.info("handler(): error on sending msg from MQTT to WebSocket...");
//            e.printStackTrace();
//        }
    }

}