package com.smn.restlog.dto;

import java.io.Serializable;
import java.util.Date;

public interface ILogDTO extends Serializable {
    String get_id();
    void setDateCreated(Date dateCreated);
}
