package com.smn.restlog.queue;

import com.smn.restlog.dto.BasicLogDTO;

import java.io.Serializable;

public class MongoLog implements Serializable {

    private String collection;
    private BasicLogDTO log;

    public MongoLog(String collection, BasicLogDTO log) {
        this.collection = collection;
        this.log = log;
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public BasicLogDTO getLog() {
        return log;
    }

    public void setLog(BasicLogDTO log) {
        this.log = log;
    }
}
