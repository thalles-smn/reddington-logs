package com.smn.restlog.exception;

public class CollectionNotFoundException extends BaseResourceException {

    private final Object[] parameters;

    public CollectionNotFoundException( Object... parameters ){
        this.parameters = parameters;
    }

    @Override
    public Object[] getParameters() {
        return parameters;
    }
}
