package com.back.repository;


import com.back.document.Post;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface PostRepository extends ElasticsearchRepository<Post,String> {
    List<Post> findAll(); //elasticSearch 사용 시 기본 리턴타입이 Iterable 타입이기 때문에 명시적으로 타입을 선언해준다.

}