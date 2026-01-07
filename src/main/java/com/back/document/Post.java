package com.back.document;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.OffsetDateTime;

/**

 Persistable: 엔티티/도큐먼트가 “new 인지”를 직접 정의하고 싶을 때 사용하는 인터페이스
 Spring Data JPA의 save()는 신규/기존을 판단해 신규면 persist(INSERT), 기존이면 merge(UPDATE 성격)로 처리
 Elasticsearch(Spring Data Elasticsearch)는 JPA처럼 영속성 컨텍스트(상태 추적/dirty checking)가 없어서
 저장 시점에 신규 여부를 단발성으로 판단해야 한다.
 특히 ES는 _id를 애플리케이션이 미리 채우는 경우가 많아(id != null) 기본 판정이 깨질 수 있으므로,
 Persistable#isNew()에서 createdAt, version 플래그 등으로 신규 여부를 정의한다.

 */
@Document(indexName = "posts")
@Data //lombok을 이용한 toString 간소화
@EqualsAndHashCode(callSuper = true)// 부모 클래스 필드 포함
@ToString(callSuper = true)// 부모 클래스 필드 포함
public class Post extends BaseDocument<String> {
    //id를 부모에서 정의하므로 삭제
    //@Id
    //private String id; 
    @Field(type = FieldType.Text)
    private String title;
    @Field(type = FieldType.Text)
    private String content;
    @Field(type = FieldType.Keyword)
    private String author;


    public Post(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

}