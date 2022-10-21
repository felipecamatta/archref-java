package com.felipecamatta.archref.infrastructure.http.client;

import com.felipecamatta.archref.infrastructure.http.entities.CharacterEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "character-service", url = "${character-service.url}")
public interface CharacterFeignClient {

    @GetMapping
    List<CharacterEntity> findAll();

}
