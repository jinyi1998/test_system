package com.test.testsystem.dto;

import lombok.Data;

import java.math.BigInteger;

@Data
public class UserQuestionRightCount {
    private Integer questionId;
    private String questionName;
    private Integer rightCount;
    private Integer knowledgeId;

    public UserQuestionRightCount() {
    }

    public UserQuestionRightCount(Integer questionId, String questionName, Integer knowledgeId,Integer rightCount) {
        this.questionId = questionId;
        this.questionName = questionName;
        this.rightCount = rightCount.intValue();
        this.knowledgeId = knowledgeId;
    }
}
