package kz.man.mirrorapi.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table
public class Room {

    @Id
    @GeneratedValue(generator = "room_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequenceName = "room_seq_id", name = "room_seq",
            allocationSize = 1)
    private Long id;

    private String name;

    private double temperature;

    private double humidity;

    private int light;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "home_id", foreignKey = @ForeignKey(name = "room_home_fk"))
    private Home home;

}
