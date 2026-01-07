package com.back.document;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.OffsetDateTime;
import java.util.Objects;

@Getter
@ToString
//ElasticSearch에서 사용할 베이스 Document 클래스
//JPA BaseEntity와 비교해 Persistable 구현이 추가로 필요하다.
//ElasticSearch용 Document 클래스에서는 @Field(type, format) 매핑이 필요하다.
public class BaseDocument<ID> implements Persistable<ID> {

    @Id
    private ID id;

    @Field(
            type = FieldType.Date,
            format = DateFormat.date_time
    )
    @CreatedDate
    private OffsetDateTime createdAt;

    @Field(
            type = FieldType.Date,
            format = DateFormat.date_time
    )
    @LastModifiedDate
    private OffsetDateTime updatedAt;

    @Override
    public boolean isNew() {
        // id가 null이거나 createdAt과 updatedAt 모두 null인 경우 새로 생성된 것으로 간주(true 반환)
        // JPA: 영속성 컨텍스트가 엔티티의 상태(transient, managed, detached)를 관리
        // Elasticsearch: 영속성 컨테스트가 없으므로 isNew() 메서드로 새 문서인지 판단
        return id == null || (createdAt == null && updatedAt == null);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BaseDocument<?> that = (BaseDocument<?>) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}