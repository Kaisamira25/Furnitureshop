package com.poly.exception;

import lombok.Data;

@Data

public class TokenException extends RuntimeException{
    String message;

    public TokenException(String message) {
        this.message = message;
    }
}
