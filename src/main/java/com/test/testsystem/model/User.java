package com.test.testsystem.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.annotation.Generated;
import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
    private String gender;
    private Integer create_by;
    private Date create_time;
    private Integer update_by;
    private Date update_time;
    private Integer is_delete;

}
