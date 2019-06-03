package kz.man.mirrorapi.rest;

import kz.man.mirrorapi.domain.Room;
import kz.man.mirrorapi.pojo.Sensor;
import kz.man.mirrorapi.publisher.MQTTPublishClient;
import kz.man.mirrorapi.repository.RoomRepository;
import kz.man.mirrorapi.service.RoomService;
import kz.man.mirrorapi.service.WebSocketService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/room")
@CrossOrigin
public class RestRoom {
    private static final Logger logger = Logger.getLogger(RestRoom.class);

    @Autowired
    private MQTTPublishClient publishSample;

    @Autowired
    WebSocketService webSocketService;

    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private RoomService roomService;

    @GetMapping(value = "/all")
    public List<Room> getAll() {
        return roomRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public Room getOne(@PathVariable Long id) {
        return roomRepository.getOne(id);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Room room) {
        return roomService.save(room);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@RequestBody Room room, @PathVariable Long id) {
        return roomService.update(room, id);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        return roomService.delete(id);
    }

//    @PostMapping(value = "/send")
//    public ResponseEntity<?> send(@RequestParam(value = "topic") String topic,
//                                  @RequestBody Sensor sensor) {
//        if (sensor != null) {
//            mqttPublisher.publish(topic, sensor);
//            return new ResponseEntity<Room>(HttpStatus.CREATED);
//        } else {
//            return new ResponseEntity<Room>(HttpStatus.CONFLICT);
//        }
//
//    }

//    @RequestMapping(value = "/sendToSTOMP", method = RequestMethod.GET)
//    public ResponseEntity sendMessage(@RequestParam("topic") String topic, @RequestParam("content") String content) {
//        webSocketService.sendMessage(topic, content);
//        return new ResponseEntity<Room>(HttpStatus.CREATED);    }

//    @PostMapping(value = "/publishToMQTT")
//    public ResponseEntity publish(@RequestParam(value = "topic") String topic,
//                                  @RequestBody Sensor sensor) {
//        publishSample.send(topic, sensor);
//        return new ResponseEntity<Room>(HttpStatus.CREATED);
//    }

    @PostMapping(value = "/publish")
    public ResponseEntity publish(@RequestBody Sensor sensor) {
        publishSample.send(sensor);
        return new ResponseEntity<Room>(HttpStatus.CREATED);
    }

}
