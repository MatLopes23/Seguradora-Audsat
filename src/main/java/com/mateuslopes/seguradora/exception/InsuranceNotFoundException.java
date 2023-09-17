package com.mateuslopes.seguradora.exception;

import java.io.Serial;

public class InsuranceNotFoundException extends EntityNotFoundException {
    @Serial
    private static final long serialVersionUID = 1L;
    public InsuranceNotFoundException() {
        super("Insurance not found.");
    }
}
