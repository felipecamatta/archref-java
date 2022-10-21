package com.felipecamatta.archref.application.amqp.outbound;

import com.felipecamatta.archref.infrastructure.amqp.dto.PersonChangeEvent;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class PersonEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public void publishPersonChangeEvent(String action, String personId) {
        PersonChangeEvent personChangeEvent = new PersonChangeEvent(action, personId);
        rabbitTemplate.convertAndSend("persons.v1.person-created", personChangeEvent);
    }
}
