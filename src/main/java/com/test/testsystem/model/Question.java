package com.test.testsystem.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
@Data
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String question_name;
    private String question_options;
    private String question_correct_answer;
    private Integer question_level;
    private Integer status;
    private Integer create_by;
    private Date create_time;
    private Integer update_by;
    private Date update_time;
    private Integer is_delete;
    private Integer knowledge_id;
}
