package com.test.testsystem.dto;

import lombok.Data;

@Data
public class KnowledegeQuestionCount {
        private Integer knowledgeId;
        private String knowledgeName;
        private Integer questionId;
        private Integer totalCount;

    public KnowledegeQuestionCount() {
    }

    public KnowledegeQuestionCount(Integer knowledgeId, String knowledgeName, Integer questionId, Integer totalCount) {
        this.knowledgeId = knowledgeId;
        this.knowledgeName = knowledgeName;
        this.questionId = questionId;
        this.totalCount = totalCount;
    }
}
