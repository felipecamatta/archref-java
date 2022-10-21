package com.felipecamatta.archref.domain.ports;

public interface PersonEvent {
    void publishPersonChangeEvent(String action, String personId);
}
