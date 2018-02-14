package com.smn.restlog.exception;

public class MongoLogOnMessageException extends BaseResourceException {

    private final Object[] parameters;

    public MongoLogOnMessageException(Object... parameters){
        this.parameters = parameters;
    }

    @Override
    public Object[] getParameters() {
        return parameters;
    }
}
