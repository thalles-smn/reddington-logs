package com.smn.restlog.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BasicLogDTO implements Serializable {

    private String _id;
    private String sender;
    private String operation;
    private Object details;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "pt-BR", timezone = "America/Sao_Paulo")
    private Date dateCreated;

    public BasicLogDTO(){

    }

    public BasicLogDTO(String sender, String operation, Object details) {
        this.sender = sender;
        this.operation = operation;
        this.details = details;
    }

    public String get_id() {
        return _id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Object getDetails() {
        return details;
    }

    public void setDetails(Object details) {
        this.details = details;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

}
