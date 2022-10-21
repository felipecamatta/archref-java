package com.felipecamatta.archref.domain.exceptions;

import com.felipecamatta.archref.configuration.MessageConfig;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
public class ErrorMessage {

    private String code;
    private String message;

    public ErrorMessage(String code, Object... args) {
        this.code = code;
        this.message = new MessageConfig().getMessage(code, args);
    }

}
