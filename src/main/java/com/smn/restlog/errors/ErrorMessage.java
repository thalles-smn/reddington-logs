package com.smn.restlog.errors;

import java.io.Serializable;

public class ErrorMessage implements Serializable {
    private static final long serialVersionUID = 1L;
    private String userMessage;
    private String developerMessage;
    private int errorCode;
    private final String moreInfo;

    public ErrorMessage(String userMessage, String developerMessage, int errorCode) {
        this.userMessage = userMessage;
        this.developerMessage = developerMessage;
        this.errorCode = errorCode;
        this.moreInfo = "http://www.developer.apiluiza.com.br/errors";
    }

    public String getUserMessage() {
        return this.userMessage;
    }

    public String getDeveloperMessage() {
        return this.developerMessage;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public String getMoreInfo() {
        return this.moreInfo;
    }
}
