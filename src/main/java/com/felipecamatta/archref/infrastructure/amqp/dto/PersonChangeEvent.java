package com.felipecamatta.archref.infrastructure.amqp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PersonChangeEvent {

    private String action;
    private String personId;

}
