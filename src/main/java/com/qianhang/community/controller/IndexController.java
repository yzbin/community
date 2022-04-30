package com.qianhang.community.controller;

import com.qianhang.community.dto.PaginationDTO;
import com.qianhang.community.mapper.UserMapper;
import com.qianhang.community.model.User;
import com.qianhang.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


@Controller
public class IndexController {
    @Autowired
    private QuestionService questionService;

    @RequestMapping("/")
    public String index(Model model,
                        @RequestParam(value = "page", defaultValue = "1") Integer page,
                        @RequestParam(value = "size", defaultValue = "5") Integer size){
        PaginationDTO pageination = questionService.list(page,size);
        model.addAttribute("pageination", pageination);
        return "index";
    }
    }
