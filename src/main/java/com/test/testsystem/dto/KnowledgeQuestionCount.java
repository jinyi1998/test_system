package com.test.testsystem.dto;

import lombok.Data;

import java.math.BigInteger;

@Data
public class KnowledgeQuestionCount {
        private Integer knowledgeId;
        private String knowledgeName;
        private Integer questionId;
        private BigInteger totalCount;

    public KnowledgeQuestionCount() {
    }

    public KnowledgeQuestionCount(Integer knowledgeId, String knowledgeName, Integer questionId, BigInteger totalCount) {
        this.knowledgeId = knowledgeId;
        this.knowledgeName = knowledgeName;
        this.questionId = questionId;
        this.totalCount = totalCount;
    }
}
