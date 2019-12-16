package com.test.testsystem.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author:Amos
 * @date:2019-12-22
 **/
@Data
@Entity
public class UserQuestions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;

    private Integer questionId;

    private Integer userQuestionStatus;
}
