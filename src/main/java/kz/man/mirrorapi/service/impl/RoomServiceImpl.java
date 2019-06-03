package kz.man.mirrorapi.service.impl;

import kz.man.mirrorapi.domain.Room;
import kz.man.mirrorapi.pojo.Sensor;
import kz.man.mirrorapi.repository.RoomRepository;
import kz.man.mirrorapi.rest.RestRoom;
import kz.man.mirrorapi.service.RoomService;
import kz.man.mirrorapi.util.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomRepository roomRepository;


    public static final Logger logger = LoggerFactory.getLogger(RestRoom.class);

    @Override
    public ResponseEntity<Room> save(Room room) {
        return new ResponseEntity<Room>(roomRepository.saveAndFlush(room), HttpStatus.CREATED);
    }

    @Override
    @SuppressWarnings("unchecked")
    public ResponseEntity update(Room room, Long id) {

        if (roomRepository.getOne(id) != null) {

            Room roomToUpdate = roomRepository.getOne(id);
            roomToUpdate.setName(room.getName());
            roomToUpdate.setTemperature(room.getTemperature());
            roomToUpdate.setHumidity(room.getHumidity());
            roomToUpdate.setLight(room.getLight());
            return new ResponseEntity<Room>(roomRepository.saveAndFlush(roomToUpdate), HttpStatus.ACCEPTED);
        } else {
            logger.error("Update error " + room.getName());
            return new ResponseEntity(
                    new CustomErrorType("Error while updating room"),
                    HttpStatus.CONFLICT);
        }
    }

    @Override
    @Transactional
    public void changeState(Sensor sensor) {
        if (roomRepository.getOne(sensor.getRoomId()) != null) {

            Room roomToUpdate = roomRepository.getOne(sensor.getRoomId());
            switch (sensor.getParam()){
                case "temperature":
                    if((int)roomToUpdate.getTemperature() != (int)sensor.getValue()){
                        roomToUpdate.setTemperature(sensor.getValue());
                    }
                    break;
                case "humidity":
                    if((int)roomToUpdate.getHumidity() != (int)sensor.getValue()){
                        roomToUpdate.setHumidity(sensor.getValue());
                    }
                    break;
                case "light":
                    if(roomToUpdate.getLight() != (int)sensor.getValue()){
                        roomToUpdate.setLight((int)sensor.getValue());
                    }
                    break;

            }
            roomRepository.saveAndFlush(roomToUpdate);
        } else {
            logger.error("Change state error " + roomRepository.getOne(sensor.getRoomId()).getName());
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public ResponseEntity delete(Long id) {

        if (roomRepository.getOne(id) != null) {
            roomRepository.deleteById(id);
            return new ResponseEntity<Room>(HttpStatus.ACCEPTED);
        } else {
            logger.error("Delete error " + roomRepository.getOne(id).getName());
            return new ResponseEntity(
                    new CustomErrorType("Error while deleting room"),
                    HttpStatus.CONFLICT);
        }
    }
}
