package com.smn.restlog.resource;

import com.smn.restlog.dto.ApiLogDTO;
import com.smn.restlog.dto.BasicLogDTO;
import com.smn.restlog.queue.MongoLogProducer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Api( value = "Fila Logs", tags = "Fila Logs")
@RestController
@RequestMapping("/queue")
public class QueueResource extends BaseResource {

    @Autowired
    private MongoLogProducer mongoLogProducer;

    @ApiOperation(value = "Create Basic Log", code = 201, response = ResponseMeta.class,  produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PostMapping("/{collection}")
    @ResponseBody
    public ResponseEntity<?> create(@PathVariable("collection") @ApiParam(value = "collection log") String collection, @RequestBody BasicLogDTO logDTO){

        mongoLogProducer.produce(collection, logDTO);

        return buildResponse(HttpStatus.CREATED, Optional.of("Log successfully added to queue"));
    }

    @ApiOperation(value = "Create Many Basic Log", code = 201, response = ResponseMeta.class, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PostMapping("/many/{collection}")
    @ResponseBody
    public ResponseEntity<?> createMany(@PathVariable("collection") @ApiParam(value = "collection log") String collection, @RequestBody List<BasicLogDTO> basicLogDTOList){

        for ( BasicLogDTO logDTO : basicLogDTOList ){
            mongoLogProducer.produce(collection, logDTO);
        }

        return buildResponse(HttpStatus.CREATED, Optional.of("Logs successfully added to queue"));
    }

    @ApiOperation(value = "Create API Log", code = 201, response = ResponseMeta.class, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PostMapping("/api/{collection}")
    @ResponseBody
    public ResponseEntity<?> createApiLog(@PathVariable("collection") @ApiParam(value = "collection log") String collection, @RequestBody ApiLogDTO logDTO){

        mongoLogProducer.produce(collection, logDTO);

        return buildResponse(HttpStatus.CREATED, Optional.of("Log API successfully added to queue"));
    }

    @ApiOperation(value = "Create Many API Log", code = 201, response = ResponseMeta.class, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PostMapping("/api/many/{collection}")
    @ResponseBody
    public ResponseEntity<?> createManyApiLog(@PathVariable("collection") @ApiParam(value = "collection log") String collection, @RequestBody List<ApiLogDTO> basicLogDTOList){

        for ( ApiLogDTO logDTO : basicLogDTOList ){
            mongoLogProducer.produce(collection, logDTO);
        }

        return buildResponse(HttpStatus.CREATED, Optional.of("Logs API successfully added to queue"));
    }

}
