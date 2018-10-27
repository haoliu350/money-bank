package com.fitch.mb.rest.dto;

/**
 * Created by Administrator on 2016/7/5.
 */
public class ErrorResponse implements ApiResponse {

    private String errorMessage;

    public ErrorResponse(){

    }

    public ErrorResponse(String errorMessage){

        this.errorMessage = errorMessage;

    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }



}
