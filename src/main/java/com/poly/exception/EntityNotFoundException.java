package com.poly.exception;

import lombok.Data;

@Data
public class EntityNotFoundException extends RuntimeException{
    String message;
    public EntityNotFoundException(String message) {
        this.message = message;
    }
}
