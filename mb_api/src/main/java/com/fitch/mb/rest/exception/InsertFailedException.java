package com.fitch.mb.rest.exception;

/**
 * Created by hliu on 2016/7/5.
 */
public class InsertFailedException extends RuntimeException{
    public InsertFailedException(String message){
        super(message);
    }
}
