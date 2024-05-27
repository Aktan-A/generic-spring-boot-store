package com.store.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ResourceExistsException extends ResponseStatusException {
    public ResourceExistsException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
