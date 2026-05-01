package com.example.springnews.repository;

import com.example.springnews.model.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Integer> {
//    뉴스글 작성
//    뉴스글 전체 읽기
//    id 로 글 한 개 읽기
//    뉴스글 삭제
//    뉴스글 변경
//    뉴스 글 내용에서 검색
//    글 한 개를 읽은 경우에는 cnt 변경
}
