package com.fitch.mb.rest.service;

import com.fitch.mb.rest.dao.MBOracleDao;
import com.fitch.mb.rest.dto.ApiResponse;
import com.fitch.mb.rest.dto.TestResponse;
import com.fitch.mb.rest.exception.IdNotFoundCustomException;
import com.fitch.mb.rest.util.OracleConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by hliu on 2016/7/5.
 */

@Service
public class MBServiceImpl implements MBService {

    @Autowired
    MBOracleDao mbOracleDao;



}
