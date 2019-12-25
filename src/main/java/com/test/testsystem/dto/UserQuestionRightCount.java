package com.test.testsystem.dto;

import lombok.Data;

@Data
public class UserQuestionRightCount {
    private Integer questionId;
    private String questionName;
    private Integer rightCount;
    private Integer knowledgeId;

    public UserQuestionRightCount() {
    }

    public UserQuestionRightCount(Integer questionId, String questionName, Integer rightCount,Integer knowledgeId) {
        this.questionId = questionId;
        this.questionName = questionName;
        this.rightCount = rightCount;
        this.knowledgeId = knowledgeId;
    }
}
