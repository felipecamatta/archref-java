package com.felipecamatta.archref.domain.ports;

import com.felipecamatta.archref.domain.entities.Character;

import java.util.List;

public interface CharacterHttp {

    List<Character> findAll();
}
