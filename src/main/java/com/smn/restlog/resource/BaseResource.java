package com.smn.restlog.resource;

import com.smn.restlog.exception.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@CrossOrigin
public class BaseResource {

    public BaseResource() {
    }

    protected void checkNotNull(Optional<?> optional, String resourceName) {
        if (!optional.isPresent() || optional.get() instanceof Collection && ((Collection)optional.get()).isEmpty() || optional.get() instanceof Page && !((Page)optional.get()).hasContent()) {
            throw new EntityNotFoundException(resourceName);
        }
    }

    protected ResponseEntity<?> buildResponse(HttpStatus status) {
        return this.buildResponse(status, Optional.empty());
    }

    protected ResponseEntity<?> buildResponse(HttpStatus status, Optional<?> entity) {
        if (entity.isPresent() && entity.get() instanceof Page) {
            Page<?> page = (Page)entity.get();
            return this.buildResponse(status, page);
        } else {
            return this.buildResponse(status, entity, 0, 50);
        }
    }

    private ResponseEntity<?> buildResponse(HttpStatus status, Page<?> page) {
        return this.buildResponse(status, (MultiValueMap)null, Optional.ofNullable(page.getContent()), page.getNumber(), page.getNumberOfElements(), null);
    }

    protected ResponseEntity<?> buildResponse(HttpStatus status, Optional<?> entity, Integer page, Integer size) {
        return this.buildResponse(status, (MultiValueMap)null, entity, page, size, null);
    }

    private ResponseEntity<?> buildResponse(HttpStatus status, MultiValueMap<String, String> headers, Optional<?> entity, Integer offset, Integer limit, Integer totalRecords) {
        List<Object> records = new ArrayList();
        if (entity.isPresent()) {
            if (entity.get() instanceof Collection) {
                records.addAll((Collection)entity.get());
            } else {
                records.add(entity.get());
            }
        }

        offset = offset == null ? 0 : offset;
        limit = limit == null ? records.size() : limit;
        ResponseMeta body = new ResponseMeta();
        body.setMeta(new Meta(getServer(), limit, offset, records.size(), totalRecords));
        body.setRecords(records);
        return new ResponseEntity(body, headers, status);
    }

    private static String getServer() {
        try {
            return InetAddress.getLocalHost().toString();
        } catch (Exception var1) {
            return "unkown";
        }
    }

}
