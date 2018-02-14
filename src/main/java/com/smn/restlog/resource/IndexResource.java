package com.smn.restlog.resource;

import com.smn.restlog.dto.ApiLogDTO;
import com.smn.restlog.dto.BasicLogDTO;
import com.smn.restlog.exception.CollectionNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@Api(value = "Logs", tags = "Logs")
@RestController
@RequestMapping("/logs")
public class IndexResource extends BaseResource {

    @Autowired
    private MongoTemplate mongo;

    @ApiOperation(value = "List collections of logs", code = 200, response = ResponseMeta.class, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping
    @ResponseBody
    public ResponseEntity<?> collections(){
        return buildResponse(HttpStatus.OK, Optional.ofNullable(mongo.getCollectionNames()));
    }

    @ApiOperation(value = "List basic logs of collection", code = 200, response = ResponseMeta.class, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("/{collection}")
    @ResponseBody
    public ResponseEntity<?> logs( @PathVariable("collection") @ApiParam(value = "collection log") String collection ){
        return buildResponse(HttpStatus.OK, Optional.ofNullable(mongo.findAll(BasicLogDTO.class, collection)));
    }

    @ApiOperation(value = "Find basic log of collection and id", code = 200, response = ResponseMeta.class, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("/{collection}/{id}")
    @ResponseBody
    public ResponseEntity<?> findLog( @PathVariable("collection") @ApiParam(value = "collection log") String collection, @PathVariable("id") @ApiParam(value = "id log") String id ){
        return buildResponse(HttpStatus.OK, Optional.ofNullable(mongo.findById(id, BasicLogDTO.class, collection)));
    }

    @ApiOperation(value = "List API logs of collection", code = 200, response = ResponseMeta.class, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("/api/{collection}")
    @ResponseBody
    public ResponseEntity<?> logsApi( @PathVariable("collection") @ApiParam(value = "collection log") String collection ){
        return buildResponse(HttpStatus.OK, Optional.ofNullable(mongo.findAll(ApiLogDTO.class, collection)));
    }

    @ApiOperation(value = "Find API log of collection and id", code = 200, response = ResponseMeta.class, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("/api/{collection}/{id}")
    @ResponseBody
    public ResponseEntity<?> findLogApi( @PathVariable("collection") @ApiParam(value = "collection log") String collection, @PathVariable("id") @ApiParam(value = "id log") String id  ){
        return buildResponse(HttpStatus.OK, Optional.ofNullable(mongo.findById(id, ApiLogDTO.class, collection)));
    }

    @ApiOperation(value = "Create basic log", code = 201, response = ResponseMeta.class, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PostMapping("/{collection}")
    @ResponseBody
    public ResponseEntity<?> logsInsert( @PathVariable("collection") @ApiParam(value = "collection log") String collection ,@RequestBody BasicLogDTO basicLogDTO){

        basicLogDTO.setDateCreated(new Date());
        mongo.insert(basicLogDTO, collection);

        return buildResponse(HttpStatus.CREATED, Optional.of(basicLogDTO));

    }

    @ApiOperation(value = "Create API log", code = 201, response = ResponseMeta.class, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PostMapping("/api/{collection}")
    @ResponseBody
    public ResponseEntity<?> logsApiInsert( @PathVariable("collection") @ApiParam(value = "collection log") String collection ,@RequestBody ApiLogDTO apiLogDTO){

        apiLogDTO.setDateCreated(new Date());
        mongo.insert(apiLogDTO, collection);

        return buildResponse(HttpStatus.CREATED, Optional.of(apiLogDTO));

    }

    @ApiOperation(value = "Delete collection by name", code = 200, response = ResponseMeta.class, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @DeleteMapping("/{collection}")
    @ResponseBody
    public ResponseEntity<?> deleteCollection( @PathVariable("collection") @ApiParam(value = "collection log") String collection ) {

        if ( mongo.collectionExists(collection) ) {
            mongo.dropCollection(collection);

            return buildResponse(HttpStatus.OK, Optional.of("successfully deleted"));
        }

        throw new CollectionNotFoundException(collection);
    }
}
