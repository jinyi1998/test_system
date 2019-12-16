package com.test.testsystem.dto;

import lombok.Data;
import org.omg.CORBA.INTERNAL;

/**
 * @author:Amos
 * @date:2019-12-11
 **/
@Data
public class UserQuestionDto {
    private Integer userId;
    private Integer questionId;

    private String questionName;

    private Integer userQuestionStatus;

    private String knowledgeName;
    public UserQuestionDto(){

    }
    public UserQuestionDto(Integer userId,Integer questionId,String questionName,Integer userQuestionStatus,String knowledgeName ){
        this.knowledgeName = knowledgeName;
        this.userId = userId;
        this.questionName = questionName;
        this.questionId = questionId;
        this.userQuestionStatus = userQuestionStatus;
    }


}