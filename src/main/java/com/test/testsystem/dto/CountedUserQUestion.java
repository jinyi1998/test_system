package com.test.testsystem.dto;

import lombok.Data;

import java.math.BigInteger;
@Data
public class CountedUserQUestion {

    private Integer userId;
    private String knowledgeName;
    private Integer knowledgeId;
    private BigInteger total;

    public CountedUserQUestion(){

    }
    public CountedUserQUestion(Integer userId,String knowledgeName,Integer knowledgeId,BigInteger total){
        this.userId=userId;
        this.knowledgeName=knowledgeName;
        this.knowledgeId=knowledgeId;
        this.total=total;
    }
}
