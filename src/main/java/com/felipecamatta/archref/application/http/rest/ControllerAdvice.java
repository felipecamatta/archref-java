package com.felipecamatta.archref.application.http.rest;

import com.felipecamatta.archref.domain.exceptions.ErrorMessage;
import com.felipecamatta.archref.domain.exceptions.PersonNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler {

    private static final Integer JVM_MAX_STRING_LEN = 2147483647;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        List<ErrorMessage> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach(error -> errors.add(createError(error)));
        return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {PersonNotFoundException.class})
    protected ResponseEntity<Object> handleNotFoundException(PersonNotFoundException exception, WebRequest request) {
        return handleExceptionInternal(exception, null, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    private ErrorMessage createError(ObjectError error) {
        String field = "";
        if (error instanceof FieldError) {
            field = ((FieldError) error).getField();
        }

        if (error.getCode().equals("Size")) {
            Integer min = null;
            Integer max = null;
            if (error.getArguments().length > 2) {
                Integer rawMax = (Integer) error.getArguments()[1];
                max = rawMax == JVM_MAX_STRING_LEN ? null : rawMax;

                Integer rawMin = (Integer) error.getArguments()[2];
                min = rawMin == 0 ? null : rawMin;
            }

            if (min != null && max != null) {
                return new ErrorMessage(error.getDefaultMessage(), field, min, max);
            } else if (min != null) {
                return new ErrorMessage(error.getDefaultMessage(), field, min);
            } else if (max != null) {
                return new ErrorMessage(error.getDefaultMessage(), field, max);
            }
        }

        return new ErrorMessage(error.getDefaultMessage(), field);
    }
}
