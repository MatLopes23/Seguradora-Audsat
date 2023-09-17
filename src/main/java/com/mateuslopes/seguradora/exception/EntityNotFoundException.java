package com.mateuslopes.seguradora.exception;

import java.io.Serial;

public class EntityNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
    protected EntityNotFoundException(String message) {
        super(message);
    }
}
