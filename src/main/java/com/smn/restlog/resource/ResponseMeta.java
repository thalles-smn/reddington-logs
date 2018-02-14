package com.smn.restlog.resource;

import java.io.Serializable;
import java.util.List;

public class ResponseMeta implements Serializable {
    private static final long serialVersionUID = 1323278432780170679L;
    private Meta meta;
    private List<?> records;

    public ResponseMeta() {
    }

    public Meta getMeta() {
        return this.meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<?> getRecords() {
        return this.records;
    }

    public void setRecords(List<?> records) {
        this.records = records;
    } {
        this.records = records;
    }
}
