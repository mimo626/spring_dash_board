package com.example.springnews.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// 프로젝트 전체에서 발생하는 에러를 감지하는 어노테이션입니다.
@ControllerAdvice
public class GlobalExceptionHandler {

    // Exception.class는 모든 종류의 에러를 다 잡겠다는 뜻입니다.
    @ExceptionHandler(Exception.class)
    public String handleAllExceptions(Exception ex, Model model) {

        // 개발자가 콘솔에서 에러 원인을 볼 수 있도록 출력 (선택 사항)
        ex.printStackTrace();

        // 에러 메시지를 화면에 전달합니다.
        model.addAttribute("errorMessage", ex.getMessage());

        // 사용자에게 보여줄 친절한 에러 페이지의 이름을 반환합니다.
        return "errorView";
    }
}