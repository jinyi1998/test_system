package com.test.testsystem.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
@Data
@Entity
public class Knowledge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String knowledge_name;
    private Integer status;
    private Integer create_by;
    private Date create_time;
    private Integer update_by;
    private Date update_time;
    private Integer is_delete;
}
