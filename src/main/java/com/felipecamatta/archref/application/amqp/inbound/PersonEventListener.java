package com.felipecamatta.archref.application.amqp.inbound;

import com.felipecamatta.archref.configuration.ApplicationConfig;
import com.felipecamatta.archref.infrastructure.amqp.dto.PersonChangeEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PersonEventListener {

    private final ApplicationConfig applicationConfig;

    @RabbitListener(queues = "#{@applicationConfig.getPersonCreatedQueue()}")
    public void onPersonChange(PersonChangeEvent personChangeEvent) {
        System.out.println(personChangeEvent.toString());
    }
}
