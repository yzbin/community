package com.qianhang.community.service;

import com.qianhang.community.dto.PaginationDTO;
import com.qianhang.community.dto.QuestionDTO;
import com.qianhang.community.mapper.QuestionMapper;
import com.qianhang.community.mapper.UserMapper;
import com.qianhang.community.model.Question;
import com.qianhang.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMpper;

    @Autowired
    private UserMapper userMapper;

    public PaginationDTO list(Integer page, Integer size) {
        Integer offset = size * (page - 1);


        List<Question> questions = questionMpper.list(offset, size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        PaginationDTO paginationDTO = new PaginationDTO();
        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        Integer totalount = questionMpper.count();
        paginationDTO.setPageination(totalount,page,size);
        return paginationDTO;
    }


    public PaginationDTO list(Integer userId, Integer page, Integer size) {
        Integer offset = size * (page - 1);
        List<Question> questions = questionMpper.listByUserId(userId,offset, size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        PaginationDTO paginationDTO = new PaginationDTO();
        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        Integer totalount = questionMpper.countByUserId(userId);
        paginationDTO.setPageination(totalount,page,size);
        return paginationDTO;
    }

    public QuestionDTO getById(Integer id) {
        Question question = questionMpper.getById(id);
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        User user = userMapper.findById(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if(question.getId() == null){
            // 创建
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMpper.create(question);
        }else {
            // 更新
            question.setGmtModified(question.getGmtCreate());
            questionMpper.update(question);
        }
    }
}
