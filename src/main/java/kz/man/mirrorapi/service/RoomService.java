package kz.man.mirrorapi.service;

import kz.man.mirrorapi.domain.Room;
import kz.man.mirrorapi.pojo.Sensor;
import org.springframework.http.ResponseEntity;

public interface RoomService {

    ResponseEntity<Room> save(Room post);

    ResponseEntity update(Room post, Long id);

    void changeState(Sensor sensor);

    ResponseEntity delete(Long id);

}
