package kz.man.mirrorapi.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table
public class Home {

    @Id
    @GeneratedValue(generator = "home_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequenceName = "home_seq_id", name = "home_seq",
            allocationSize = 1)
    private Long id;

    private String name;

    private String address;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "home_user_fk"))
    private User holder;
}
