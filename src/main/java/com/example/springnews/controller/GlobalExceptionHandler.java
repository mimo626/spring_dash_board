package com.example.springnews.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handleAllExceptions(Exception ex, Model model) {

        ex.printStackTrace();

        model.addAttribute("errorMessage", ex.getMessage());

        return "errorView";
    }
}