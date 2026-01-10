package com.back.global.initData;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration  // @Bean으로 등록된 것들은 스프링 컨테이너가 관리
public class BaseInitData {

    @Bean
    public ApplicationRunner baseInitDataRunner() {
        return args -> {
            System.out.println("ApplicationRunner 빈은 스프링에 등록되면 자동으로 실행됩니다");
        };
    }
}

/*
Spring Context 로딩 완료
→ Bean 생성 완료
→ ApplicationRunner 자동 실행 ✅
 */
