package com.api.rest.sem2_1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Resource already exists")
public class ResourceConflictException extends RuntimeException{
    public ResourceConflictException(String message){
        super(message);
    }

}
