package com.example.springnews.repository;

import com.example.springnews.model.entity.News;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Integer> {
//    뉴스글 작성 save(news)

//    뉴스글 전체 읽기 findAll()

//    id 로 글 한 개 읽기
    public News findNewsById(int id);

    public List<News> findNewsByWriter(String writer);

//    뉴스글 삭제
    @Transactional
    public int deleteNewsById(int id);

//    뉴스글 변경 save(news)

//    뉴스 글 내용에서 검색
    @Query("SELECT n FROM News n WHERE n.content LIKE %:content%")
    public List<News> searchNews(String content);

//    글 한 개를 읽은 경우에는 cnt 변경
    @Modifying
    @Transactional
    @Query("UPDATE News n SET n.cnt = :cnt + 1 WHERE n.id = :id")
    int updateNewsCnt(@Param("id") int id, @Param("cnt") int cnt);

}
