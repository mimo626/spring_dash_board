package com.example.springnews.controller;

import com.example.springnews.model.entity.News;
import com.example.springnews.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class NewsController {

    private final NewsRepository newsR;

//    URL을 입력하여 요청 -> 전체 뉴스 출력 (매핑명 : /newsmain)
    @RequestMapping("/newsMain")
    public ModelAndView newsMain(
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {

        ModelAndView mav = new ModelAndView();
        Page<News> newsPage = newsR.findAll(pageable); // List 대신 Page 사용
        mav.addObject("newsPage", newsPage);
        mav.setViewName("newsView");
        return mav;
    }
//    뉴스 제목을 선택하여 요청 -> 뉴스 id 로 해당 뉴스 내용 출력 (매핑명 : /one)
    @GetMapping(value = "/one", produces = "application/json; charset=utf-8")
    @ResponseBody
    public News one(int id){
        News news = newsR.findNewsById(id);

        if(news != null) {
            newsR.updateNewsCnt(news.getId(), news.getCnt());
            news.setCnt(news.getCnt() + 1);
        }

        return news;
    }
//    삭제 버튼을 클릭하여 요청 -> 뉴스 id 로 뉴스 삭제 (매핑명 : /delete)
    @GetMapping("/delete")
    public String delete(int id){
        newsR.deleteNewsById(id);

        return "redirect:/newsMain";
    }
//    검색 요청 -> 전달된 검색어로 뉴스글 내용 에서 검색하여 결과 출력 (매핑명 : /search)
    @GetMapping("/search")
    public ModelAndView search(
            String content,
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {

        ModelAndView mav = new ModelAndView();

        if(content == null || content.trim().isEmpty()){
            mav.setViewName("redirect:/newsMain");
            return mav;
        }

        Page<News> newsPage = newsR.searchNews(content, pageable);
        mav.addObject("newsPage", newsPage);
        mav.addObject("content", content); // 페이지 이동 시 검색어 유지를 위해 추가
        mav.setViewName("newsView");

        return mav;
    }
//    리스트에 출력된 작성자 이름을 클릭하여 요청 -> 작성자가 작성한 뉴스 글만 출력 (매핑명 : /writer)
    @GetMapping("/writer/{writer}")
    public ModelAndView writer(
            @PathVariable("writer") String writer,
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {

        ModelAndView mav = new ModelAndView();
        System.out.println(writer);

        if(writer != null && !writer.isEmpty()){
            Page<News> newsPage = newsR.findNewsByWriter(writer, pageable);
            mav.addObject("newsPage", newsPage);
            mav.addObject("writer", writer);
            mav.setViewName("newsView");
        } else {
            mav.setViewName("redirect:/newsMain");
        }
        return mav;
    }
//    POST 방식
//    뉴스 작성 (매핑명 : /insert)
    @PostMapping("/insert")
    public String insert(News news) {
        // 제목이 비어있지 않을 때만 저장 로직 실행
        if(news.getTitle() != null && !news.getTitle().trim().isEmpty()) {
            news.setCnt(0); // 초기 조회수 0 세팅
            newsR.save(news); // DB에 저장
        }

        return "redirect:/newsMain";
    }
//    뉴스 수정 (매핑명 : /update)
    @PostMapping(value = "/update", produces = "application/json; charset=utf-8")
    public String update(News news){
        News existingNews = newsR.findById(news.getId()).orElse(null);

        if (existingNews != null) {
            existingNews.setTitle(news.getTitle());
            existingNews.setWriter(news.getWriter());
            existingNews.setContent(news.getContent());

            newsR.save(existingNews);
        }

        return "redirect:/newsMain";
    }

}
