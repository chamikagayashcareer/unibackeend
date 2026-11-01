package com.uni.unibakeend.errors;

public class NotFoundResourceException extends RuntimeException {

    public NotFoundResourceException(String message) {
        super(message);
    }

}
