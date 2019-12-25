package com.test.testsystem.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.test.testsystem.dao.QuestionRepos;
import com.test.testsystem.dao.UserQuestionRepos;
import com.test.testsystem.dto.KnowledegeQuestionCount;
import com.test.testsystem.dto.UserQuestionDto;
import com.test.testsystem.dto.UserQuestionRightCount;
import com.test.testsystem.model.Question;
import com.test.testsystem.model.User;
import com.test.testsystem.model.UserQuestions;
import com.test.testsystem.service.QuestionService;
import com.test.testsystem.utils.EntityUtils;
import com.test.testsystem.utils.JsonResult;
import com.test.testsystem.utils.UserQuestionUtils;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.*;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionRepos questionRepos;
    @Autowired
    private UserQuestionRepos userQuestionRepos;

    @Override
    public Question getQuestionById(Integer id) {
        return questionRepos.findById(id).get();
    }

    @Override
    public JsonResult saveQuestion(Question question) {
        if(null==question.getId()||0==question.getId()){
            question.setUpdateTime(new Date());
            question.setCreateTime(new Date());
            question.setStatus(1);
        }
        return JsonResult.success(questionRepos.saveAndFlush(question));
    }

    @Override
    public JsonResult deleteQuestion(Integer id) {
        questionRepos.deleteById(id);
        return JsonResult.success(null);
    }

    @Override
    public List<Question> getQuestionList() {
        return questionRepos.findAll();

    }

    @Override
    public JsonResult getPageQuestionList(Integer page,Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<Question>questions=questionRepos.findAll();
        PageInfo pageInfo=new PageInfo(questions);
        return JsonResult.success(pageInfo);

    }

    @Override
    public JsonResult getNextQuestion(Integer userId) {
        //1.获取当前用户答题记录
        Integer questionSelected = 0;
        User user = new User();
        user.setId(userId);
        List<UserQuestionDto> userQuestionDtos = getUserQuestionKnowledgeList(user);
        List<Integer> questionIds = new ArrayList<>();
        List<Integer> userIds = new ArrayList<>();
        userIds.add(userId);
        for (UserQuestionDto userQuestionDto:userQuestionDtos){
            questionIds.add(userQuestionDto.getQuestionId());
        }
        //2.获取包含当前用户做过的题目的用户列表
        List<UserQuestions> userQuestions = userQuestionRepos.findAllByQuestionIdIn(questionIds);
        //排除本用户的答题记录
        List<UserQuestions> noUserUserQuestions = new ArrayList<>();
        for (UserQuestions userQuestions1:userQuestions){
            if (userQuestions1.getUserId() != userId){
                noUserUserQuestions.add(userQuestions1);
            }
        }
        userQuestions = noUserUserQuestions;
        //对答题记录按照用户ID 进行分组
           HashMap<Integer,List<UserQuestions>> map = new HashMap<>();
           for (UserQuestions userQuestions1:userQuestions){
               if (map.containsKey(userQuestions1.getUserId())){
                   List<UserQuestions> questions = map.get(userQuestions1.getUserId());
                   questions.add(userQuestions1);
                   map.put(userQuestions1.getUserId(),questions);
               }else {
                   List<UserQuestions> questions = new ArrayList<>();
                   questions.add(userQuestions1);
                   map.put(userQuestions1.getUserId(),questions);

               }
           }
           //得到最接近的userId 也就是最接近的一组数据
        Integer userNearId = UserQuestionUtils.getUserNearedQuestions(map,questionIds);
           List<UserQuestions>  userQuestionsSelected = map.get(userNearId);
           List<Integer> selecedQuestionIds = new ArrayList<>();
           if (null == userQuestionsSelected){
               List<Question> questions = questionRepos.findAll();
               Integer questionRandomId = new Random().nextInt(questions.size()-1)+1;
               while (questionIds.contains(questionRandomId)){
                   questionRandomId = new Random().nextInt(questions.size());
               }
               questionSelected = questionRandomId;
               System.out.println(questionRandomId);
               Question question = questionRepos.findById(questionSelected).get();
               return JsonResult.success(question);
           }
           for (UserQuestions userQuestions1:userQuestionsSelected){
               selecedQuestionIds.add(userQuestions1.getQuestionId());
           }
           //拿掉本用户已经做过的题目
           selecedQuestionIds.removeAll(questionIds);
           if (selecedQuestionIds.size() > 0){
               questionSelected = selecedQuestionIds.get(0);
           }else {
                List<Question> questions = questionRepos.findAll();
               Integer questionRandomId = new Random().nextInt(questions.size()-1)+1;
                while (questionIds.contains(questionRandomId)){
                    questionRandomId = new Random().nextInt(questions.size());
                }
               questionSelected = questionRandomId;
           }
           Question question = questionRepos.findById(questionSelected).get();
        return JsonResult.success(question);
    }


    @Override
    public List<UserQuestionDto> getUserQuestionKnowledgeList(User user) {

        return EntityUtils.castEntity(questionRepos.getUserQuestionKnowledgeList(user.getId()),UserQuestionDto.class,new UserQuestionDto());

    }

    @Override
    public List<KnowledegeQuestionCount> getKnowledegeQuestionCount(User user) {
        List<Object[]> objects = questionRepos.getKnowledgeQuestionCount();
        return EntityUtils.castEntity(objects,KnowledegeQuestionCount.class,new KnowledegeQuestionCount());
    }

    @Override
    public List<UserQuestionRightCount> getUserQuestionRightCount(User user) {
        List<Object[]> objects = questionRepos.getUserRightCount(user.getId());
        List<UserQuestionRightCount> userQuestionRightCounts = new ArrayList<>();
        for (Object[] objects1:objects){
            UserQuestionRightCount userQuestionRightCount = new UserQuestionRightCount();
            userQuestionRightCount.setQuestionId((Integer) objects1[0]);
            userQuestionRightCount.setKnowledgeId(((Integer) objects1[1]).intValue());
            userQuestionRightCount.setRightCount(((BigInteger) objects1[2]).intValue());

            userQuestionRightCount.setQuestionName((String) objects1[3]);
            userQuestionRightCounts.add(userQuestionRightCount);
        }
        return userQuestionRightCounts;
    }

    @Override
    public JsonResult saveUserQuestion(UserQuestions userQuestions) {
        userQuestionRepos.save(userQuestions);
        return JsonResult.success();
    }
}
