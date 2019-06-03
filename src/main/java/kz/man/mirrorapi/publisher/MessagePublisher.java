package kz.man.mirrorapi.publisher;

import kz.man.mirrorapi.pojo.Sensor;

public interface MessagePublisher {

    void publish(String topic, Sensor sensor);

}
