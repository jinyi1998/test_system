package com.test.testsystem.dto;

import lombok.Data;
import org.omg.CORBA.INTERNAL;

@Data
public class UserQuestionDto {
    private Integer userId;
    private Integer questionId;

    private String questionName;

    private Integer userQuestionStatus;

    private String knowledgeName;

    private Integer knowledgeId;
    public UserQuestionDto(){

    }

    public UserQuestionDto(Integer userId,Integer questionId,String questionName,Integer userQuestionStatus,String knowledgeName,Integer knowledgeId ){
        this.knowledgeName = knowledgeName;
        this.userId = userId;
        this.questionName = questionName;
        this.questionId = questionId;
        this.userQuestionStatus = userQuestionStatus;
        this.knowledgeId = knowledgeId;
    }


}
