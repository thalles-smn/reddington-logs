package com.smn.restlog.queue;

import com.smn.restlog.dto.ApiLogDTO;

import java.io.Serializable;

public class MongoApiLog implements Serializable {

    private String collection;
    private ApiLogDTO log;

    public MongoApiLog(String collection, ApiLogDTO log) {
        this.collection = collection;
        this.log = log;
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public ApiLogDTO getLog() {
        return log;
    }

    public void setLog(ApiLogDTO log) {
        this.log = log;
    }
}
