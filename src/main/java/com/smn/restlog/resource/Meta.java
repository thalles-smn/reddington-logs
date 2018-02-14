package com.smn.restlog.resource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Meta {

    @JsonIgnore
    private final Log LOGGER;
    private String version;
    private String server;
    private Integer limit;
    private Integer offset;
    private Integer recordCount;
    private Integer totalRecords;

    public Meta(String server, Integer limit, Integer offset, Integer recordCount, Integer totalRecords) {
        this.LOGGER = LogFactory.getLog(Meta.class);
        this.server = server;
        this.limit = limit;
        this.offset = offset;
        this.recordCount = recordCount;
        this.totalRecords = totalRecords;
    }

    public Meta(String version, String server, Integer limit, Integer offset, Integer recordCount, Integer totalRecords) {
        this(server, limit, offset, recordCount, totalRecords);
        this.version = version;
    }

    public String getVersion() {
        return this.version;
    }

    public String getServer() {
        return this.server;
    }

    public Integer getLimit() {
        return this.limit;
    }

    public Integer getOffset() {
        return this.offset;
    }

    public Integer getRecordCount() {
        return this.recordCount;
    }

    public Integer getTotalRecords() {
        return this.totalRecords;
    }
}
