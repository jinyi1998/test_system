package com.test.testsystem.service;

import com.test.testsystem.model.Knowledge;
import com.test.testsystem.utils.JsonResult;
import java.util.List;

public interface KnowledgeService {

   Knowledge getKnowledgeById(Integer id);

   JsonResult saveKnowledge(Knowledge Knowledge);

   JsonResult deleteKnowledge(Integer id);

   List<Knowledge> getKnowledgeList();

   JsonResult getPageKnowledgeList(Integer page,Integer pageSize);


}
