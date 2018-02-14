package com.smn.restlog.handlers;

import com.smn.restlog.errors.ErrorMessage;
import com.smn.restlog.errors.ErrorMessageBuilder;
import com.smn.restlog.exception.CollectionNotFoundException;
import com.smn.restlog.exception.MongoLogOnMessageException;
import com.smn.restlog.logger.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private ErrorMessageBuilder errorMessageBuilder;

    @Autowired
    private Logger logger;

    @ExceptionHandler({CollectionNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorMessage handleCollectionNotFound(CollectionNotFoundException ex, WebRequest request) {

        logger.capture(ex);

        return errorMessageBuilder
                .withDeveloperMessage("collection not found")
                .withUserMessage("collection not found")
                .withErrorCode(404)
                .build();
    }

    @ExceptionHandler({MongoLogOnMessageException.class})
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public ErrorMessage handleMongoLogOnMessage(MongoLogOnMessageException ex, WebRequest request) {

        logger.capture(ex);

        return errorMessageBuilder
                .withDeveloperMessage("unable to process log in queue")
                .withUserMessage("unable to process log in queue")
                .withErrorCode(422)
                .build();
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorMessage handleAll(Exception ex, WebRequest request) {

        logger.capture(ex);

        return errorMessageBuilder
                .withDeveloperMessage("error ocurred " + ex.getClass().getName() + ": " + ex.getLocalizedMessage())
                .withUserMessage("unknown error ocurred")
                .withErrorCode(500)
                .build();
    }

}
