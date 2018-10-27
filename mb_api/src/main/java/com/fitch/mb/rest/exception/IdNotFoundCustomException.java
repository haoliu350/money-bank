package com.fitch.mb.rest.exception;

/**
 * Created by hliu on 2016/7/5.
 */
public class IdNotFoundCustomException extends RuntimeException{
    public IdNotFoundCustomException(String message){
        super(message);
    }
}
