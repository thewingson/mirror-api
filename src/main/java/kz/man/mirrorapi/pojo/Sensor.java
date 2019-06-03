package kz.man.mirrorapi.pojo;

import lombok.Data;

@Data
public class Sensor {

    private Long id;
    private String topic;
    private Long homeId;
    private Long roomId;
    private String param;
    private double value;

}
