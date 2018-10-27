package com.fitch.mb.rest.controller;

import com.fitch.mb.rest.dto.ErrorResponse;
import com.fitch.mb.rest.exception.IdNotFoundCustomException;
import com.fitch.mb.rest.exception.InsertFailedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;


/**
 * Created by hliu on 2016/7/5.
 */
@ControllerAdvice
public class GlobalControllerExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);

    @ExceptionHandler(IdNotFoundCustomException.class)
    public ResponseEntity<ErrorResponse> handleTestCustomException(Exception exception) {
        LOGGER.error("[IdNotFoundCustomException]: " + exception.getMessage());
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InsertFailedException.class)
    public ResponseEntity<ErrorResponse> handleInsertFailedException(Exception exception) {
        LOGGER.error("[InsertFailedException]: " + exception.getMessage());
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ErrorResponse> handleSQLException(Exception exception) {
        LOGGER.error("[SQLException]: " + exception.getMessage());
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<ErrorResponse> handleNumberFormatException(Exception exception) {
        LOGGER.error("[NumberFormatException]: " + exception.getMessage());
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

}
