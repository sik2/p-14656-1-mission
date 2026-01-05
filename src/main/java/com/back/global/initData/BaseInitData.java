package com.back.global.initData;

import com.back.domain.post.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration  // 설정 클래스 지정
@Slf4j
@RequiredArgsConstructor
public class BaseInitData {

    private final PostService postService;

    // Bean을 통해 애플리케이션 시작 시 초기화 로직 실행
    @Bean
    public ApplicationRunner baseInitDataRunner (){
        return args->{
            work1();
        };
    }

    private void work1(){
        log.debug("Post entity 개수: {}",postService.count());
    }
}
