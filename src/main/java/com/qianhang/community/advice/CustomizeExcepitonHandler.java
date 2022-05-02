package com.qianhang.community.advice;

import com.qianhang.community.exception.CustomizeException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CustomizeExcepitonHandler {
    @ExceptionHandler(Exception.class)
    ModelAndView handleControllerException(Throwable ex, Model model) {
        if(ex instanceof CustomizeException){
            model.addAttribute("message",ex.getMessage());
        }else {
            model.addAttribute("message","服务冒烟，稍后再尝试");
        }
        return new ModelAndView("error");
    }

}
