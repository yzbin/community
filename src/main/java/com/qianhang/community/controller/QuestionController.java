package com.qianhang.community.controller;

import com.qianhang.community.dto.CommentDTO;
import com.qianhang.community.dto.QuestionDTO;
import com.qianhang.community.enums.CommentTypeEnum;
import com.qianhang.community.service.CommentService;
import com.qianhang.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private CommentService commentService;
    @GetMapping("/question/{id}")
    public String question(@PathVariable(name="id") Integer id, Model model){
        QuestionDTO questionDTO = questionService.getById(id);
        List<CommentDTO> comments =  commentService.listByTargetId(id, CommentTypeEnum.QUESTION);
        // 累加阅读数
        questionService.incView(id);
        model.addAttribute("question",questionDTO);
        model.addAttribute("comments",comments);
        return "question";
    }
}
