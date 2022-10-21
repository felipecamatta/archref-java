package com.felipecamatta.archref.infrastructure.http.adapter;

import com.felipecamatta.archref.domain.entities.Character;
import com.felipecamatta.archref.domain.ports.CharacterHttp;
import com.felipecamatta.archref.infrastructure.http.client.CharacterFeignClient;
import com.felipecamatta.archref.infrastructure.http.entities.CharacterEntity;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.List;

@AllArgsConstructor
public class CharacterHttpImpl implements CharacterHttp {

    private final CharacterFeignClient characterFeignClient;

    @Override
    public List<Character> findAll() {
        List<CharacterEntity> characters = characterFeignClient.findAll();
        return characters.stream()
                .map(characterEntity -> {
                    Character character = new Character();
                    BeanUtils.copyProperties(characterEntity, character);
                    return character;
                }).toList();
    }
}
