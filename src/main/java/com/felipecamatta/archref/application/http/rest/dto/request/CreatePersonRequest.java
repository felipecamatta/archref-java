package com.felipecamatta.archref.application.http.rest.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

import static com.felipecamatta.archref.domain.exceptions.MessageErrorCodeConstants.FIELD_REQUIRED;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CreatePersonRequest {

    @NotBlank(message = FIELD_REQUIRED)
    private String name;

}
