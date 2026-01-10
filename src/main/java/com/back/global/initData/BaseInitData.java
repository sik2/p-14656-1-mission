package com.back.global.initData;

import com.back.domain.post.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class BaseInitData {
    private final PostService postService;
    @Bean
    public ApplicationRunner baseInitDataRunner (){
        return args->{
            work1();
        };
    }
    private void work1() {
        log.info("INFO 로그 보이나?");
        log.debug("DEBUG 로그 보이나?");
    }
}

/*
Spring Context 로딩 완료
→ Bean 생성 완료
→ ApplicationRunner 자동 실행 ✅
 */
