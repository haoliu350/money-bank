package com.fitch.mb.rest.service;

import com.fitch.mb.rest.dto.ApiResponse;

/**
 * Created by hliu on 2016/7/5.
 */
public interface TestService {

    public ApiResponse testServiceException(int id, String name, String desc);
    public String getSysdate();

}
