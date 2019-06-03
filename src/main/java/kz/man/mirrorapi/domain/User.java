package kz.man.mirrorapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "usr")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {

    @Id
    @GeneratedValue(generator = "user_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequenceName = "user_seq_id", name = "user_seq",
            allocationSize = 1)
    private Long id;

    private String email;

    private String username;

    private String password;

    private String firstname;

    private String lastname;

    private String middlename;

    private int age;

    private boolean active;

}
