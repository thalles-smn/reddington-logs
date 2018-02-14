package com.smn.restlog.exception;

public class EntityNotFoundException extends BaseResourceException {
    private static final long serialVersionUID = -1366479614080419696L;

    public EntityNotFoundException(Object... parameters) {
        super(parameters);
    }
}
