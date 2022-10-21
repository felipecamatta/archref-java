package com.felipecamatta.archref.infrastructure.amqp.adapter;

import com.felipecamatta.archref.domain.ports.PersonEvent;
import com.felipecamatta.archref.application.amqp.outbound.PersonEventPublisher;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PersonAmqpImpl implements PersonEvent {

    private final PersonEventPublisher personEventPublisher;

    @Override
    public void publishPersonChangeEvent(String action, String personId) {
        personEventPublisher.publishPersonChangeEvent(action, personId);
    }
}
