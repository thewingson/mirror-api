package kz.man.mirrorapi.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table
public class Post {

    @Id
    @GeneratedValue(generator = "post_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequenceName = "post_seq_id", name = "post_seq",
            allocationSize = 1)
    private Long id;

    private String title;

    private String text;


}
