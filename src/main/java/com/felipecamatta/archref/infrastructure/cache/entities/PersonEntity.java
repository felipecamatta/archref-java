package com.felipecamatta.archref.infrastructure.cache.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@RedisHash("person")
public class PersonEntity {

    @Id
    private String id;

    private String name;

}
