package com.back.global.initData;

import com.back.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.back.document.Post;

import java.time.LocalDateTime;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class BaseInitData {

    private final PostService postService;

    @Bean
    public ApplicationRunner baseInitDataRunner() {
        return args -> {
            log.debug("ApplicationRunner 빈은 스프링에 등록되면 자동으로 실행됩니다");
            work1();
            work2();
            work3("FB4FjZsBX2e7fBMydzwH");// 기생성된 post1의 id
            work3("1");// 잘못된 id
            work4();
            work5();
        };
    }

    private void work1() {
        log.debug("Post entity 개수: {}", postService.count());
        if (postService.count() == 0) {
            for (int i = 1; i <= 10; i++) {
                String title = "Sample Post Title " + i;
                String content = "This is the content of sample post number " + i + ".";
                String author = "Author" + i;
                Post post = postService.create(title, content, author);
                log.debug("Created Post: {}", post);
            }
        }
    }

    private void work2() {
        log.debug("기존 Post 전체 조회");
        for (Post post : postService.findAll()) {
            log.debug("Existing Post: {}", post);
        }
    }

    private void work3(String id) {
        log.debug("Post 단건 조회");
        try {
            Post post = postService.findById(id);
            log.debug("조회된 Post: {}", post);
        } catch (com.back.exception.PostNotFoundException ex) {
            log.warn("조회 실패: {}", ex.getMessage());
        }
    }

    private void work4(){
        log.debug("Post 단건 수정");
        for (Post post : postService.findAll()) {
            String newTitle = post.getTitle() + " [Updated]";
            String newContent = post.getContent() + " This content has been updated on "+ LocalDateTime.now();
            Post updatedPost = postService.update(post.getId(), newTitle, newContent);
            log.debug("Updated Post: {}", updatedPost);
        }
    }

    private void work5(){
        log.debug("Post 삭제");
        for (Post post : postService.findAll()) {
            postService.delete(post.getId());
            log.debug("Deleted Post: {}", post.getId());
        }
        log.debug("삭제 후 Post 개수: {}", postService.count());
    }

}