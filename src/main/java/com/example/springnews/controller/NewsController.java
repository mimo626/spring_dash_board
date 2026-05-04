package com.example.springnews.controller;

import com.example.springnews.model.entity.News;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class NewsController {
//    GET 방식
//    URL을 입력하여 요청 -> 전체 뉴스 출력 (매핑명 : /newsmain)
    @RequestMapping("/newsMain")
    public ModelAndView newsMain(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("newsView");
        return mav;
    }
//    뉴스 제목을 선택하여 요청 -> 뉴스 id 로 해당 뉴스 내용 출력 (매핑명 : /one)
    @RequestMapping("/one")
    public String one(int id){
        return "";
    }
//    삭제 버튼을 클릭하여 요청 -> 뉴스 id 로 뉴스 삭제 (매핑명 : /delete)
    @RequestMapping("/delete")
    public String delete(int id){
        return "";
    }
//    검색 요청 -> 전달된 검색어로 뉴스글 내용 에서 검색하여 결과 출력 (매핑명 : /search)
    @RequestMapping("/search")
    public String search(String keyword){
        return "";
    }
//    리스트에 출력된 작성자 이름을 클릭하여 요청 -> 작성자가 작성한 뉴스 글만 출력 (매핑명 : /writer)
    @RequestMapping("/writer")
    public String writer(String writer){
        return "";
    }
//    POST 방식
//    뉴스 작성 (매핑명 : /insert)
    @RequestMapping(value = "/insert", produces = "application/json; charset=utf-8")
    public String insert(News news){
        return "";
    }
//    뉴스 수정 (매핑명 : /update)
    @RequestMapping(value = "/update", produces = "application/json; charset=utf-8")
    public String update(News news){
        return "";
    }

}
